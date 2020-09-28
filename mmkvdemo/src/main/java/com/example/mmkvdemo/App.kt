package com.example.mmkvdemo

import android.app.Application
import com.tencent.mmkv.MMKV

class App:Application() {

    companion object {
        private var INSTANCE: App? = null
        fun instance() = INSTANCE!!
    }

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        INSTANCE = this
    }
}