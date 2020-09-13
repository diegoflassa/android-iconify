package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class MaterialCommunityModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-material-community-1.4.57.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MaterialCommunityIcons.values().size)
        for(icon in MaterialCommunityIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}