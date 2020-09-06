package com.joanzapata.iconify

import android.content.Context
import android.widget.TextView
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper
import com.joanzapata.iconify.internal.ParsingUtil.parse
import java.util.*

object Iconify {
    /** List of icon font descriptors  */
    private val iconFontDescriptors: MutableList<IconFontDescriptorWrapper> = ArrayList()

    /**
     * Add support for a new icon font.
     * @param iconFontDescriptor The IconDescriptor holding the ttf file reference and its mappings.
     * @return An initializer instance for chain calls.
     */
    fun with(iconFontDescriptor: IconFontDescriptor): IconifyInitializer {
        return IconifyInitializer(iconFontDescriptor)
    }

    /**
     * Replace "{}" tags in the given text views with actual icons, requesting the IconFontDescriptors
     * one after the others.
     *
     *
     * **This is a one time call.** If you call [TextView.setText] after this,
     * you'll need to call it again.
     * @param textViews The TextView(s) to enhance.
     */
    fun addIcons(vararg textViews: TextView?) {
        for (textView in textViews) {
            if (textView == null) continue
            textView.text = compute(textView.context, textView.text, textView)
        }
    }

    private fun addIconFontDescriptor(iconFontDescriptor: IconFontDescriptor) {

        // Prevent duplicates
        for (wrapper in iconFontDescriptors) {
            if (wrapper.iconFontDescriptor.ttfFileName()
                == iconFontDescriptor.ttfFileName()
            ) {
                return
            }
        }

        // Add to the list
        iconFontDescriptors.add(IconFontDescriptorWrapper(iconFontDescriptor))
    }

    @JvmOverloads
    fun compute(context: Context?, text: CharSequence?, target: TextView? = null): CharSequence? {
        return parse(context!!, iconFontDescriptors, text, target)
    }

    /**
     * Finds the Typeface to apply for a given icon.
     * @param icon The icon for which you need the typeface.
     * @return The font descriptor which contains info about the typeface to apply, or null
     * if the icon cannot be found. In that case, check that you properly added the modules
     * using [.with]} prior to calling this method.
     */
    @JvmStatic
    fun findTypefaceOf(icon: Icon?): IconFontDescriptorWrapper? {
        for (iconFontDescriptor in iconFontDescriptors) {
            if (iconFontDescriptor.hasIcon(icon!!)) {
                return iconFontDescriptor
            }
        }
        return null
    }

    /**
     * Retrieve an icon from a key,
     * @return The icon, or null if no icon matches the key.
     */
    @JvmStatic
    fun findIconForKey(iconKey: String?): Icon? {
        var i = 0
        val iconFontDescriptorsSize = iconFontDescriptors.size
        while (i < iconFontDescriptorsSize) {
            val iconFontDescriptor = iconFontDescriptors[i]
            val icon = iconFontDescriptor.getIcon(iconKey!!)
            if (icon != null) return icon
            i++
        }
        return null
    }

    /**
     * Allows chain calls on [Iconify.with].
     */
    class IconifyInitializer(iconFontDescriptor: IconFontDescriptor) {
        /**
         * Add support for a new icon font.
         * @param iconFontDescriptor The IconDescriptor holding the ttf file reference and its mappings.
         * @return An initializer instance for chain calls.
         */
        @Suppress("unused")
        fun with(iconFontDescriptor: IconFontDescriptor): IconifyInitializer {
            addIconFontDescriptor(iconFontDescriptor)
            return this
        }

        init {
            addIconFontDescriptor(iconFontDescriptor)
        }
    }
}