package com.joanzapata.iconify.sample

import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.fonts.*
import com.joanzapata.iconify.sample.FontIconsViewPagerAdapter.FontWithTitle

enum class Font(override val title: String, override val font: IconFontDescriptor) : FontWithTitle {
    FONTAWESOME("FontAwesome", FontAwesomeModule()), ENTYPO("Entypo", EntypoModule()),
    TYPICONS("Typicons", TypiconsModule()), IONICONS("Ionicons", IoniconsModule()),
    MATERIAL("Material", MaterialModule()), MATERIALCOMMUNITY("Material Community", MaterialCommunityModule()),
    METEOCONS("Meteocons", MeteoconsModule()), WEATHERICONS("WeatherIcons", WeathericonsModule()),
    SIMPLELINEICONS("SimpleLineIcons", SimpleLineIconsModule());

}