package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class FontAwesomeModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-fontawesome-4.5.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(FontAwesomeIcons.values().size)
        for (icon in FontAwesomeIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}