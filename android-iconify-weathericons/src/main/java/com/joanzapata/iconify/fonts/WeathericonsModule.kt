package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class WeathericonsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-weathericons-2.0.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(WeathericonsIcons.values().size)
        for(icon in WeathericonsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}