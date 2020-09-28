package com.system.databaselibrary.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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
