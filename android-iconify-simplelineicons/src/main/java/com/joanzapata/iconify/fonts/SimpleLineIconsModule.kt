package com.joanzapata.iconify.fonts

import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor

class SimpleLineIconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconify/android-iconify-simplelineicons-1.0.0.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(SimpleLineIconsIcons.values().size)
        for(icon in SimpleLineIconsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}