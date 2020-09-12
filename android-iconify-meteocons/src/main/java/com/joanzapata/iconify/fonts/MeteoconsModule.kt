package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class MeteoconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-meteocons-previous.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MeteoconsIcons.values().size)
        for(icon in MeteoconsIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}