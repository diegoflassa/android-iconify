package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class MaterialCommunityModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-material-community-1.4.57.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MaterialCommunityIcons.values().size)
        for(icon in MaterialCommunityIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}