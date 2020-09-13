package io.github.diegoflassa.iconivoid.sample

import io.github.diegoflassa.iconivoid.IconFontDescriptor
import io.github.diegoflassa.iconivoid.sample.FontIconsViewPagerAdapter.FontWithTitle
import io.github.diegoflassa.iconivoid.fonts.*

enum class Font(override val title: String, override val font: IconFontDescriptor) : FontWithTitle {
    FONTAWESOME("FontAwesome", FontAwesomeModule()),
    ENTYPO("Entypo", EntypoModule()),
    TYPICONS("Typicons", TypiconsModule()),
    IONICONS("Ionicons", IoniconsModule()),
    MATERIAL("Material", MaterialModule()),
    MATERIALCOMMUNITY("Material Community", MaterialCommunityModule()),
    METEOCONS("Meteocons", MeteoconsModule()),
    WEATHERICONS("WeatherIcons", WeathericonsModule()),
    SIMPLELINEICONS("SimpleLineIcons", SimpleLineIconsModule());

}