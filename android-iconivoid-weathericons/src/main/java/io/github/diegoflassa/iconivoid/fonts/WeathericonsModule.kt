package io.github.diegoflassa.iconivoid.fonts

import io.github.diegoflassa.iconivoid.Icon
import io.github.diegoflassa.iconivoid.IconFontDescriptor

class WeathericonsModule : IconFontDescriptor {
    override fun ttfFileName(): String {
        return "iconivoid/android-iconivoid-weathericons-2.0.ttf"
    }

    override fun characters(): Array<Icon> {
        val retArray = ArrayList<Icon>(WeathericonsIcons.values().size)
        for(icon in WeathericonsIcons.values()){
            retArray.add(icon)
        }
        return retArray.toTypedArray()
    }
}