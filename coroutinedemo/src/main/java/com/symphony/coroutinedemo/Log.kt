package com.symphony.coroutinedemo

import android.util.Log

private const val TAG = "zhang"
fun D(msg:String){
    Log.d(TAG, "===> $msg")
}

fun I(msg:String){
    Log.i(TAG, "===> $msg")
}

fun E(msg:String){
    Log.e(TAG, "===> $msg")
}