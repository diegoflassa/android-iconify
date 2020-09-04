@file:Suppress("MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate")

package com.joanzapata.iconify

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.TypedValue
import com.joanzapata.iconify.Iconify.findIconForKey
import com.joanzapata.iconify.Iconify.findTypefaceOf

/**
 * Embed an icon into a Drawable that can be used as TextView icons, or ActionBar icons.
 * <pre>
 * new IconDrawable(context, IconValue.icon_star)
 * .colorRes(R.color.white)
 * .actionBarSize();
</pre> *
 * If you don't set the size of the drawable, it will use the size
 * that is given to him. Note that in an ActionBar, if you don't
 * set the size explicitly it uses 0, so please use actionBarSize().
 */
class IconDrawable : Drawable {

    private var context: Context? = null
    private var icon: Icon? = null
    private var paint: TextPaint? = null
    private var size = -1
    private var mAlpha = 255

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     * @param iconKey The icon key you want this drawable to display.
     * @throws IllegalArgumentException if the key doesn't match any icon.
     */
    constructor(context: Context, iconKey: String) {
        val icon = findIconForKey(iconKey)
            ?: throw IllegalArgumentException("No icon with that key \"$iconKey\".")
        init(context, icon)
    }

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     * @param icon    The icon you want this drawable to display.
     */
    constructor(context: Context, icon: Icon) {
        init(context, icon)
    }

    private fun init(context: Context, icon: Icon) {
        this.context = context
        this.icon = icon
        paint = TextPaint()
        val descriptor = findTypefaceOf(icon)
            ?: throw IllegalStateException(
                "Unable to find the module associated " +
                        "with icon " + icon.key() + ", have you registered the module " +
                        "you are trying to use with Iconify.with(...) in your Application?"
            )
        paint!!.typeface = descriptor.getTypeface(context)
        paint!!.style = Paint.Style.FILL
        paint!!.textAlign = Paint.Align.CENTER
        paint!!.isUnderlineText = false
        paint!!.color = Color.BLACK
        paint!!.isAntiAlias = true
    }

    /**
     * Set the size of this icon to the standard Android ActionBar.
     * @return The current IconDrawable for chaining.
     */
    fun actionBarSize(): IconDrawable {
        return sizeDp(ANDROID_ACTIONBAR_ICON_SIZE_DP)
    }

    /**
     * Set the size of the drawable.
     * @param dimenRes The dimension resource.
     * @return The current IconDrawable for chaining.
     */
    fun sizeRes(dimenRes: Int): IconDrawable {
        return sizePx(context!!.resources.getDimensionPixelSize(dimenRes))
    }

    /**
     * Set the size of the drawable.
     * @param size The size in density-independent pixels (dp).
     * @return The current IconDrawable for chaining.
     */
    fun sizeDp(size: Int): IconDrawable {
        return sizePx(convertDpToPx(context, size.toFloat()))
    }

    /**
     * Set the size of the drawable.
     * @param size The size in pixels (px).
     * @return The current IconDrawable for chaining.
     */
    fun sizePx(size: Int): IconDrawable {
        this.size = size
        setBounds(0, 0, size, size)
        invalidateSelf()
        return this
    }

    /**
     * Set the color of the drawable.
     * @param color The color, usually from android.graphics.Color or 0xFF012345.
     * @return The current IconDrawable for chaining.
     */
    fun color(color: Int): IconDrawable {
        paint!!.color = color
        invalidateSelf()
        return this
    }

    /**
     * Set the color of the drawable.
     * @param colorRes The color resource, from your R file.
     * @return The current IconDrawable for chaining.
     */
    fun colorRes(colorRes: Int): IconDrawable {
        paint!!.color = context!!.resources.getColor(colorRes)
        invalidateSelf()
        return this
    }

    /**
     * Set the alpha of this drawable.
     * @param alpha The alpha, between 0 (transparent) and 255 (opaque).
     * @return The current IconDrawable for chaining.
     */
    fun alpha(alpha: Int): IconDrawable {
        setAlpha(alpha)
        invalidateSelf()
        return this
    }

    override fun getIntrinsicHeight(): Int {
        return size
    }

    override fun getIntrinsicWidth(): Int {
        return size
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val height = bounds.height()
        paint!!.textSize = height.toFloat()
        val textBounds = Rect()
        val textValue = icon!!.character().toString()
        paint!!.getTextBounds(textValue, 0, 1, textBounds)
        val textHeight = textBounds.height()
        val textBottom = bounds.top + (height - textHeight) / 2f + textHeight - textBounds.bottom
        canvas.drawText(textValue, bounds.exactCenterX(), textBottom, paint!!)
    }

    override fun isStateful(): Boolean {
        return true
    }

    override fun setState(stateSet: IntArray): Boolean {
        val oldValue = paint!!.alpha
        val newValue = if (isEnabled(stateSet)) mAlpha else mAlpha / 2
        paint!!.alpha = newValue
        return oldValue != newValue
    }

    override fun setAlpha(alpha: Int) {
        this.mAlpha = alpha
        paint!!.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint!!.colorFilter = cf
    }

    override fun clearColorFilter() {
        paint!!.colorFilter = null
    }

    override fun getOpacity(): Int {
        return mAlpha
    }

    /**
     * Sets paint style.
     * @param style to be applied
     */
    fun setStyle(style: Paint.Style?) {
        paint!!.style = style
    }

    // Util
    private fun isEnabled(stateSet: IntArray): Boolean {
        for (state in stateSet) if (state == android.R.attr.state_enabled) return true
        return false
    }

    // Util
    private fun convertDpToPx(context: Context?, dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            context!!.resources.displayMetrics
        ).toInt()
    }

    companion object {
        const val ANDROID_ACTIONBAR_ICON_SIZE_DP = 24
    }
}