package io.github.diegoflassa.iconivoid.sample

import android.app.Application
import android.content.Context
import io.github.diegoflassa.iconivoid.IconiVoid
import java.lang.ref.WeakReference

@Suppress("unused")
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context =  WeakReference(this)
        for (font in Font.values()) IconiVoid.with(font.font)
    }

    companion object {
        private lateinit var context: WeakReference<Context>
        fun getContext(): Context {
            return context.get()!!
        }
    }
}