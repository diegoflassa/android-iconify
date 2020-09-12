package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class EntypoModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-entypo-3,2015.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(EntypoIcons.values().size)
        for(icon in EntypoIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}