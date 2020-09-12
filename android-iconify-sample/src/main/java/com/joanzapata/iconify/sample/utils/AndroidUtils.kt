@file:Suppress("unused")

package com.joanzapata.iconify.sample.utils

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display

object AndroidUtils {
    /** Returns the available screensize, including status bar and navigation bar  */
    @JvmStatic
    fun getScreenSize(context: Activity): Size {
        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display
        } else {
            context.windowManager.defaultDisplay
        }
        var realWidth: Int?
        var realHeight: Int?
        when {
            Build.VERSION.SDK_INT >= 17 -> {
                val realMetrics = DisplayMetrics()
                display?.getRealMetrics(realMetrics)
                realWidth = realMetrics.widthPixels
                realHeight = realMetrics.heightPixels
            }
            Build.VERSION.SDK_INT >= 14 -> {
                try {
                    val mGetRawH = Display::class.java.getMethod("getRawHeight")
                    val mGetRawW = Display::class.java.getMethod("getRawWidth")
                    realWidth = mGetRawW.invoke(display) as Int
                    realHeight = mGetRawH.invoke(display) as Int
                } catch (e: Exception) {
                    //this may not be 100% accurate, but it's all we've got
                    realWidth = display?.width
                    realHeight = display?.height
                    Log.e("Display Info", "Couldn't use reflection to get the real display metrics.")
                }
            }
            else -> {
                //This should be close, as lower API devices should not have window navigation bars
                realWidth = display?.width
                realHeight = display?.height
            }
        }
        return Size(realWidth!!, realHeight!!)
    }

    class Size(val width: Int, val height: Int)
}