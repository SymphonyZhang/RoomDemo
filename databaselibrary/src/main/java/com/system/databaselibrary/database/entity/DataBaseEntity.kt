package com.system.databaselibrary.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.system.databaselibrary.util.*


/**
 * 设备信息表[出厂日期，器具出厂编号，每天日期，当天最新的订单流水号，校准次数，零位校准时的ad值]
 * id:非自增长，固定为0
 */
@Entity
data class DeviceInfo(
    //@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int = 0,
    @ColumnInfo(name = "date_of_manufacture") var manufactureDate: String = "1970-01-01",
    @ColumnInfo(name = "device_no") var deviceNo: String = "123456789012",
    @ColumnInfo(name = "date") var currentDate: String = "2020-09-27",
    @ColumnInfo(name = "order_count") var orderCount: String = "0",
    @ColumnInfo(name = "calibrate_times") var calibrateTimes: Int = 0,
    @ColumnInfo(name = "init_ad") var initAd: String = "0"
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}

/**
 * 商品信息表[id，商品编号，商品名称，单价，热键组，热键值，商品类型，条形码,单位，库存]
 */
@Entity(
    indices = [Index(value = ["goods_code"], unique = true), Index(
        value = ["barcode"],
        unique = true
    ),Index(value = ["hot_key_group","hot_key"],unique = true)]
)
data class GoodsInfo(
    @ColumnInfo(name = "goods_code") var goodsCode: String = DEFAULT_GOODS_CODE,
    @ColumnInfo(name = "goods_name") var goodsName: String = DEFAULT_GOODS_NAME,
    @ColumnInfo(name = "price") var price: Float = DEFAULT_PRICE,
    @ColumnInfo(name = "hot_key_group") var hotKeyGroup: String? = null,
    @ColumnInfo(name = "hot_key") var hotKey: Int? = null,
    @ColumnInfo(name = "goods_type") var goodsType: Int = TYPE_WEIGHT,
    @ColumnInfo(name = "barcode") var ean_13: String? = null,
    @ColumnInfo(name = "unit") var unit: String = UNIT_ARRAY[goodsType],
    @ColumnInfo(name = "stock") var stock: Float = DEFAULT_STOCK
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

/**
 * 管理员用户表[id,管理员工号，管理员名称，管理员密码，超级管理员标志]
 */
@Entity(indices = [Index(value = ["user_no"], unique = true)])
data class UserInfo(
    @ColumnInfo(name = "user_no") var userNo:String = "",
    @ColumnInfo(name = "user_name") var userName:String = "",
    @ColumnInfo(name = "user_password") var userPassword:String = "",
    @ColumnInfo(name = "user_tag") var userTag:Boolean = false
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
