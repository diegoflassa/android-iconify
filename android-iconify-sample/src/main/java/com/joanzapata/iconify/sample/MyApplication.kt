package com.joanzapata.iconify.sample

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context =  WeakReference(this)
    }

    companion object {
        private lateinit var context: WeakReference<Context>
        fun getContext():Context{
            return context.get()!!
        }
    }
}