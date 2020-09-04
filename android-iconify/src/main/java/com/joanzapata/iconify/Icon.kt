package com.joanzapata.iconify

/**
 * Icon represents one icon in an icon font.
 */
interface Icon {
    /** The key of icon, for example 'fa-ok'  */
    fun key(): String?

    /** The character matching the key in the font, for example '\u4354'  */
    fun character(): Char
}