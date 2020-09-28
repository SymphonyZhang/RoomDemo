package com.symphony.roomdemo.app

import android.app.Application

class App:Application() {

    companion object {
        private var INSTANCE: App? = null
        fun instance() = INSTANCE!!
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}