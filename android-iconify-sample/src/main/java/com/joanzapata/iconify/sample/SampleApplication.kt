package com.joanzapata.iconify.sample

import android.app.Application
import com.joanzapata.iconify.Iconify

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        for (font in Font.values()) Iconify.with(font.font)
    }
}