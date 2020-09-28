package com.system.databaselibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.system.databaselibrary.database.entity.DeviceInfo
import com.system.databaselibrary.repository.DeviceInfoRepository
import kotlinx.coroutines.launch

class DeviceInfoViewModel(application: Application) :AndroidViewModel(application){
    /**
     * 数据处理类
     */
    private val deviceInfoRepository = DeviceInfoRepository.getInstance(application)

    val calibrateTimes:LiveData<Int>
        get() = deviceInfoRepository.calibrateTimes

    fun createDeviceInfo(vararg deviceInfo: DeviceInfo){
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel createDeviceInfo Thread name  :   ${Thread.currentThread().name}")
            deviceInfoRepository.createDeviceInfo(*deviceInfo)
        }
    }

    fun updateDeviceInfo(vararg deviceInfo: DeviceInfo){
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel updateDeviceInfo Thread name  :   ${Thread.currentThread().name}")
            deviceInfoRepository.updateDeviceInfo(*deviceInfo)
        }
    }

    fun getAllDeviceInfo() {
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel getAllDeviceInfo Thread name  :   ${Thread.currentThread().name}")
            val list = deviceInfoRepository.getAllDeviceInfo()
            for (device in list){
                println("zyx =====  ${device.toString()}")
            }
        }
    }

    fun deleteDeviceInfo(vararg deviceInfo: DeviceInfo){
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel deleteDeviceInfo Thread name  :   ${Thread.currentThread().name}")
            deviceInfoRepository.deleteDeviceInfo(*deviceInfo)
        }
    }

    fun updateCalibrateTimes(){
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel updateCalibrateTimes Thread name  :   ${Thread.currentThread().name}")
            deviceInfoRepository.updateCalibrateTimes()
        }
    }
    fun updateOrderCount(){
        viewModelScope.launch {
            println("zyx======================>DeviceInfoViewModel updateOrderCount Thread name  :   ${Thread.currentThread().name}")
            deviceInfoRepository.updateOrderCount()
        }
    }
}