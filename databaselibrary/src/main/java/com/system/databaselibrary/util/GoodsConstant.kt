package com.system.databaselibrary.util

import android.view.KeyEvent

/**
 * 商品默认编码
 */
const val DEFAULT_GOODS_CODE = ""

/**
 * 商品默认名称
 */
const val DEFAULT_GOODS_NAME = ""

/**
 * 商品默认单价
 */
const val DEFAULT_PRICE = 0F

/**
 * 商品快捷键组默认值
 */
val DEFAULT_HOT_KEY_GROUP = DEPARTMENT[KeyEvent.KEYCODE_F5]

/**
 * 商品快捷键默认值
 */
const val DEFAULT_HOT_KEY = -1

/**
 * 商品为计重类型
 */
const val TYPE_WEIGHT = 0

/**
 * 商品为计件类型
 */
const val TYPE_COUNT = 1

/**
 * 商品默认条形码
 */
const val DEFAULT_BARCODE = "-1"

/**
 * 商品计算单位
 */
val UNIT_ARRAY = arrayOf("kg","件")

/**
 * 商品库存
 */
const val DEFAULT_STOCK:Float = 0.0f