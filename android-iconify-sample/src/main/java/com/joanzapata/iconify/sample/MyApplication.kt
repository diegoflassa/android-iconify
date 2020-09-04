package com.joanzapata.iconify.sample

import android.app.Application
import android.content.Context


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    companion object {
        private lateinit var mContext: Context
        val context: Context
            get() = mContext
    }
}