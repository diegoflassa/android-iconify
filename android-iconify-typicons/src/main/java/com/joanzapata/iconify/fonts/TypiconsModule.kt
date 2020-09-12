package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class TypiconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-typicons-2.0.7.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(TypiconsIcons.values().size)
        for(icon in TypiconsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}