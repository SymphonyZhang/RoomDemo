package com.symphony.roomdemo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.symphony.roomdemo.R
import com.system.databaselibrary.database.entity.DeviceInfo
import com.system.databaselibrary.viewmodel.DeviceInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val deviceInfoViewModel: DeviceInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deviceInfoViewModel.calibrateTimes.observe(this, Observer {
            tv_count.text = it?.toString() ?: "0"
            println("zyx  *******************************  it    :    $it   ************************************")
        })
    }

    fun click(view: View){
        when(view){
            btn_insert -> {
                //for (i in 0..10){
                    deviceInfoViewModel.createDeviceInfo(DeviceInfo())
                //}
            }
            btn_update -> {
                //deviceInfoViewModel.updateDeviceInfo(DeviceInfo(orderCount = "100"))
                deviceInfoViewModel.updateOrderCount()
            }
            btn_query ->  deviceInfoViewModel.getAllDeviceInfo()
            btn_delete -> {
                //deviceInfoViewModel.deleteDeviceInfo(DeviceInfo(id = 5))
            }
            btn_update_count -> {
                deviceInfoViewModel.updateCalibrateTimes()
            }
        }
    }
}