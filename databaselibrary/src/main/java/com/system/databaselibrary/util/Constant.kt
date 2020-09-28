package com.system.databaselibrary.util

import android.view.KeyEvent

/**
 * 商品组
 */
const val DM0 = "D0"
const val DM1 = "D1"
const val DM2 = "D2"
const val DM3 = "D3"
const val DM4 = "D4"

val DEPARTMENT = mapOf<Int,String>(
    KeyEvent.KEYCODE_F5 to DM0,
    KeyEvent.KEYCODE_F6 to DM1,
    KeyEvent.KEYCODE_F7 to DM2,
    KeyEvent.KEYCODE_F8 to DM3,
    KeyEvent.KEYCODE_F9 to DM4
)