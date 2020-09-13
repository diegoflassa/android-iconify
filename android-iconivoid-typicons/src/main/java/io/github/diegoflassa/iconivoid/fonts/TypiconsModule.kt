package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class TypiconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-typicons-2.0.7.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(TypiconsIcons.values().size)
        for(icon in TypiconsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}