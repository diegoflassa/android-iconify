package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class IoniconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-ionicons.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(IonicIcons.values().size)
        for (icon in IonicIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}