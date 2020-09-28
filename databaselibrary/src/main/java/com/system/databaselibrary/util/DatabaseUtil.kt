package com.system.databaselibrary.util

import java.util.*

/**
 * 获取uuid并截取前面一段
 */
fun getUUID():String{
    val uuid = UUID.randomUUID().toString()
    return "-${uuid.replace("-","").substring(0,12)}"
}