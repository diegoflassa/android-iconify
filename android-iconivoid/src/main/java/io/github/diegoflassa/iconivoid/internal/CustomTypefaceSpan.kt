package io.github.diegoflassa.iconivoid.internal

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.Rect
import android.graphics.Typeface
import android.text.style.ReplacementSpan
import io.github.diegoflassa.iconivoid.Icon

class CustomTypefaceSpan(
    icon: Icon, private val type: Typeface,
    private val iconSizePx: Float, private val iconSizeRatio: Float, private val iconColor: Int,
    val isAnimated: Boolean, private val baselineAligned: Boolean
) : ReplacementSpan() {
    private val icon: String = icon.character().toString()
    private val rotationStartTime: Long = System.currentTimeMillis()
    override fun getSize(
        paint: Paint, text: CharSequence,
        start: Int, end: Int, fm: FontMetricsInt?
    ): Int {
        LOCAL_PAINT.set(paint)
        applyCustomTypeFace(LOCAL_PAINT, type)
        LOCAL_PAINT.getTextBounds(icon, 0, 1, TEXT_BOUNDS)
        if (fm != null) {
            val baselineRatio: Float = if (baselineAligned) 0F else BASELINE_RATIO
            fm.descent = (TEXT_BOUNDS.height() * baselineRatio).toInt()
            fm.ascent = -(TEXT_BOUNDS.height() - fm.descent)
            fm.top = fm.ascent
            fm.bottom = fm.descent
        }
        return TEXT_BOUNDS.width()
    }

    override fun draw(
        canvas: Canvas, text: CharSequence,
        start: Int, end: Int, x: Float, top: Int, y: Int,
        bottom: Int, paint: Paint
    ) {
        applyCustomTypeFace(paint, type)
        paint.getTextBounds(icon, 0, 1, TEXT_BOUNDS)
        canvas.save()
        val baselineRatio = if (baselineAligned) 0f else BASELINE_RATIO
        if (isAnimated) {
            val rotation =
                (System.currentTimeMillis() - rotationStartTime) / ROTATION_DURATION.toFloat() * 360f
            val centerX = x + TEXT_BOUNDS.width() / 2f
            val centerY = y - TEXT_BOUNDS.height() / 2f + TEXT_BOUNDS.height() * baselineRatio
            canvas.rotate(rotation, centerX, centerY)
        }
        canvas.drawText(
            icon,
            x - TEXT_BOUNDS.left,
            y - TEXT_BOUNDS.bottom + TEXT_BOUNDS.height() * baselineRatio, paint
        )
        canvas.restore()
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
        paint.isFakeBoldText = false
        paint.textSkewX = 0f
        paint.typeface = tf
        if (isAnimated) paint.clearShadowLayer()
        if (iconSizeRatio > 0) paint.textSize =
            paint.textSize * iconSizeRatio else if (iconSizePx > 0) paint.textSize = iconSizePx
        if (iconColor < Int.MAX_VALUE) paint.color = iconColor
    }

    companion object {
        private const val ROTATION_DURATION = 2000
        private val TEXT_BOUNDS = Rect()
        private val LOCAL_PAINT = Paint()
        private const val BASELINE_RATIO = 1 / 7f
    }

}