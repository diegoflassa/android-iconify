package io.github.diegoflassa.iconivoid.internal

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.internal.HasOnViewAttachListener.OnViewAttachListener

object ParsingUtil {
    private const val ANDROID_PACKAGE_NAME = "android"
    @JvmStatic
    fun parse(
        contextParam: Context,
        iconFontDescriptors: List<IconFontDescriptorWrapper>,
        text: CharSequence?,
        target: TextView?
    ): CharSequence? {
        var context = contextParam
        context = context.applicationContext

        // Don't do anything related to iconivoid if text is null
        if (text == null) return text

        // Analyse the text and replace {} blocks with the appropriate character
        // Retain all transformations in the accumulator
        val spannableBuilder = SpannableStringBuilder(text)
        recursivePrepareSpannableIndexes(
            context,
            text.toString(), spannableBuilder,
            iconFontDescriptors, 0
        )
        val isAnimated = hasAnimatedSpans(spannableBuilder)

        // If animated, periodically invalidate the TextView so that the
        // CustomTypefaceSpan can redraw itself
        if (isAnimated) {
            requireNotNull(target) { "You can't use \"spin\" without providing the target TextView." }
            require(target is HasOnViewAttachListener) {
                target.javaClass.simpleName + " does not implement " +
                        "HasOnViewAttachListener. Please use IconTextView, IconButton or IconToggleButton."
            }
            (target as HasOnViewAttachListener).setOnViewAttachListener(object :
                OnViewAttachListener {
                var isAttached = false
                override fun onAttach() {
                    isAttached = true
                    ViewCompat.postOnAnimation(target, object : Runnable {
                        override fun run() {
                            if (isAttached) {
                                target.invalidate()
                                ViewCompat.postOnAnimation(target, this)
                            }
                        }
                    })
                }

                override fun onDetach() {
                    isAttached = false
                }
            })
        } else if (target is HasOnViewAttachListener) {
            (target as HasOnViewAttachListener).setOnViewAttachListener(null)
        }
        return spannableBuilder
    }

    private fun hasAnimatedSpans(spannableBuilder: SpannableStringBuilder): Boolean {
        val spans =
            spannableBuilder.getSpans(0, spannableBuilder.length, CustomTypefaceSpan::class.java)
        for (span in spans) {
            if (span.isAnimated) return true
        }
        return false
    }

    private fun recursivePrepareSpannableIndexes(
        context: Context,
        fullText: String,
        textParam: SpannableStringBuilder,
        iconFontDescriptors: List<IconFontDescriptorWrapper>,
        start: Int
    ) {

        // Try to find a {...} in the string and extract expression from it
        var text = textParam
        val stringText = text.toString()
        val startIndex = stringText.indexOf("{", start)
        if (startIndex == -1) return
        val endIndex = stringText.indexOf("}", startIndex) + 1
        if (endIndex == -1) return
        val expression = stringText.substring(startIndex + 1, endIndex - 1)

        // Split the expression and retrieve the icon key
        val strokes = expression.split(" ".toRegex()).toTypedArray()
        val key = strokes[0]

        // Loop through the descriptors to find a key match
        var iconFontDescriptor: IconFontDescriptorWrapper? = null
        var icon: Icon? = null
        for (i in iconFontDescriptors.indices) {
            iconFontDescriptor = iconFontDescriptors[i]
            icon = iconFontDescriptor.getIcon(key)
            if (icon != null) break
        }

        // If no match, ignore and continue
        if (icon == null) {
            recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, endIndex)
            return
        }

        // See if any more stroke within {} should be applied
        var iconSizePx = -1f
        var iconColor = Int.MAX_VALUE
        var iconSizeRatio = -1f
        var spin = false
        var baselineAligned = false
        for (i in 1 until strokes.size) {
            val stroke = strokes[i]

            // Look for "spin"
            when {
                stroke.equals("spin", ignoreCase = true) -> {
                    spin = true
                }
                stroke.equals("baseline", ignoreCase = true) -> {
                    baselineAligned = true
                }
                stroke.matches("([0-9]*(\\.[0-9]*)?)dp".toRegex()) -> {
                    iconSizePx =
                        dpToPx(context, java.lang.Float.valueOf(stroke.substring(0, stroke.length - 2)))
                }
                stroke.matches("([0-9]*(\\.[0-9]*)?)sp".toRegex()) -> {
                    iconSizePx =
                        spToPx(context, java.lang.Float.valueOf(stroke.substring(0, stroke.length - 2)))
                }
                stroke.matches("([0-9]*)px".toRegex()) -> {
                    iconSizePx = Integer.valueOf(stroke.substring(0, stroke.length - 2)).toFloat()
                }
                stroke.matches("@dimen/(.*)".toRegex()) -> {
                    iconSizePx = getPxFromDimen(context, context.packageName, stroke.substring(7))
                    require(iconSizePx >= 0) { "Unknown resource $stroke in \"$fullText\"" }
                }
                stroke.matches("@android:dimen/(.*)".toRegex()) -> {
                    iconSizePx = getPxFromDimen(context, ANDROID_PACKAGE_NAME, stroke.substring(15))
                    require(iconSizePx >= 0) { "Unknown resource $stroke in \"$fullText\"" }
                }
                stroke.matches("([0-9]*(\\.[0-9]*)?)%".toRegex()) -> {
                    iconSizeRatio =
                        java.lang.Float.valueOf(stroke.substring(0, stroke.length - 1)) / 100f
                }
                stroke.matches("#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})".toRegex()) -> {
                    iconColor = Color.parseColor(stroke)
                }
                stroke.matches("@color/(.*)".toRegex()) -> {
                    iconColor = getColorFromResource(context, context.packageName, stroke.substring(7))
                    require(iconColor != Int.MAX_VALUE) { "Unknown resource $stroke in \"$fullText\"" }
                }
                stroke.matches("@android:color/(.*)".toRegex()) -> {
                    iconColor =
                        getColorFromResource(context, ANDROID_PACKAGE_NAME, stroke.substring(15))
                    require(iconColor != Int.MAX_VALUE) { "Unknown resource $stroke in \"$fullText\"" }
                }
                else -> {
                    throw IllegalArgumentException("Unknown expression $stroke in \"$fullText\"")
                }
            }
        }

        // Replace the character and apply the typeface
        text = text.replace(startIndex, endIndex, "" + icon.character())
        text.setSpan(
            CustomTypefaceSpan(
                icon,
                iconFontDescriptor!!.getTypeface(context)!!,
                iconSizePx, iconSizeRatio, iconColor, spin, baselineAligned
            ),
            startIndex, startIndex + 1,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        recursivePrepareSpannableIndexes(context, fullText, text, iconFontDescriptors, startIndex)
    }

    private fun getPxFromDimen(context: Context, packageName: String?, resName: String?): Float {
        val resources = context.resources
        val resId = resources.getIdentifier(
            resName, "dimen",
            packageName
        )
        return if (resId <= 0) (-1).toFloat() else resources.getDimension(resId)
    }

    private fun getColorFromResource(context: Context, packageName: String?, resName: String?): Int {
        val resources = context.resources
        val resId = resources.getIdentifier(
            resName, "color",
            packageName
        )
        return if (resId <= 0) Int.MAX_VALUE else ContextCompat.getColor(context, resId)
    }

    private fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            context.resources.displayMetrics
        )
    }

    private fun spToPx(context: Context, sp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, sp,
            context.resources.displayMetrics
        )
    }
}