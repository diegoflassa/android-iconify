package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class IoniconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-ionicons.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(IoniconsIcons.values().size)
        for (icon in IoniconsIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}