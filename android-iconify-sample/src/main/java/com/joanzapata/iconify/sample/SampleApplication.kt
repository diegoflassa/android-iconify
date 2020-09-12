package com.joanzapata.iconify.sample

import android.app.Application
import android.content.Context
import com.joanzapata.iconify.Iconify
import java.lang.ref.WeakReference

@Suppress("unused")
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context =  WeakReference(this)
        for (font in Font.values()) Iconify.with(font.font)
    }

    companion object {
        private lateinit var context: WeakReference<Context>
        fun getContext(): Context {
            return context.get()!!
        }
    }
}