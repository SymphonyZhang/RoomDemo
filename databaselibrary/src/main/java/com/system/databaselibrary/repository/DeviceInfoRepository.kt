package com.system.databaselibrary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.system.databaselibrary.database.AppDataBase
import com.system.databaselibrary.database.dao.DeviceInfoDao
import com.system.databaselibrary.database.entity.DeviceInfo


class DeviceInfoRepository(context: Context) {

    private val deviceInfoDao: DeviceInfoDao = AppDataBase.getInstance(context).deviceInfoDao()

    var calibrateTimes:LiveData<Int>

    companion object{
        @Volatile private var instant:DeviceInfoRepository? = null
        fun getInstance(context: Context)= instant ?: synchronized(this){
            instant ?: DeviceInfoRepository(context).also { instant = it }
        }


    }

    init {
        calibrateTimes = deviceInfoDao.getCalibrateTimes()
    }


    suspend fun createDeviceInfo(vararg deviceInfo: DeviceInfo) {
        println("zyx======================>DeviceInfoRepository createDeviceInfo Thread name  :   ${Thread.currentThread().name}")
        deviceInfoDao.insert(*deviceInfo)
    }

    suspend fun updateDeviceInfo(vararg deviceInfo:DeviceInfo) {
        println("zyx======================>DeviceInfoRepository updateDeviceInfo Thread name  :   ${Thread.currentThread().name}")
        deviceInfoDao.update(*deviceInfo)
    }

    suspend fun getAllDeviceInfo():List<DeviceInfo>{
        println("zyx======================>DeviceInfoRepository getAllDeviceInfo Thread name  :   ${Thread.currentThread().name}")
        return deviceInfoDao.getAllDeviceInfo()
    }

    suspend fun deleteDeviceInfo(vararg deviceInfo:DeviceInfo){
        println("zyx======================>DeviceInfoRepository deleteDeviceInfo Thread name  :   ${Thread.currentThread().name}")
        deviceInfoDao.deleteDeviceInfo(*deviceInfo)
    }

    suspend fun updateCalibrateTimes(){
        println("zyx======================>DeviceInfoRepository updateCalibrateTimes Thread name  :   ${Thread.currentThread().name}")
        deviceInfoDao.updateCalibrateTimes()
    }

    suspend fun updateOrderCount(){
        println("zyx======================>DeviceInfoRepository updateOrderCount Thread name  :   ${Thread.currentThread().name}")
        deviceInfoDao.updateOrderCount()
    }
}