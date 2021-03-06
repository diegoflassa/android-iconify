package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class MaterialModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-material-2.0.0.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MaterialIcons.values().size)
        for (icon in MaterialIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}