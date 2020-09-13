package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class MeteoconsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-meteocons-previous.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(MeteoconsIcons.values().size)
        for(icon in MeteoconsIcons.values())
            retArray.add(icon)
        return retArray.toTypedArray()
    }
}