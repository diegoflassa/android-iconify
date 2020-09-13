package io.github.diegoflassa.iconivoid

/**
 * An IconFontDescriptor defines a TTF font file
 * and is able to map keys with characters in this file.
 */
interface IconFontDescriptor {
    /**
     * The TTF file name.
     * @return a name with no slash, present in the assets.
     */
    fun ttfFileName(): String
    fun characters(): Array<Icon>
}