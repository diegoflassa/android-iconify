package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class SimpleLineIconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-simplelineicons-1.0.0.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(SimpleLineIconsIcons.values().size)
        for(icon in SimpleLineIconsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}