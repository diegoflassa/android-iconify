package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class EntypoModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-entypo-3,2015.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(EntypoIcons.values().size)
        for(icon in EntypoIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}