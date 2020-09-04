package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class FontAwesomeModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-fontawesome.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(FontAwesomeIcons.values().size)
        for (icon in FontAwesomeIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}