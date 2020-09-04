package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class MaterialModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-material.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MaterialIcons.values().size)
        for (icon in MaterialIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}