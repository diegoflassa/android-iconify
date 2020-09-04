package com.joanzapata.iconify.internal

import android.content.Context
import android.graphics.Typeface
import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconFontDescriptor
import java.util.*

class IconFontDescriptorWrapper(val iconFontDescriptor: IconFontDescriptor) {
    private val iconsByKey: MutableMap<String, Icon>
    private var cachedTypeface: Typeface? = null
    fun getIcon(key: String): Icon? {
        return iconsByKey[key]
    }

    fun getTypeface(context: Context): Typeface? {
        if (cachedTypeface != null) return cachedTypeface
        synchronized(this) {
            if (cachedTypeface != null) return cachedTypeface
            cachedTypeface =
                Typeface.createFromAsset(context.assets, iconFontDescriptor.ttfFileName())
            return cachedTypeface
        }
    }

    fun hasIcon(icon: Icon): Boolean {
        return iconsByKey.values.contains(icon)
    }

    init {
        iconsByKey = HashMap()
        val characters = iconFontDescriptor.characters()
        var i = 0
        val charactersLength = characters.size
        while (i < charactersLength) {
            val icon = characters[i]
            iconsByKey[icon.key()!!] = icon
            i++
        }
    }
}