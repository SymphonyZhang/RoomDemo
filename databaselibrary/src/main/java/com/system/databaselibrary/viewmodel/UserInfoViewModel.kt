package com.system.databaselibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.system.databaselibrary.database.entity.UserInfo
import com.system.databaselibrary.repository.UserInfoRepository
import kotlinx.coroutines.launch

class UserInfoViewModel(application: Application) : AndroidViewModel(application) {

    //数据处理类
    private val userInfoRepository = UserInfoRepository.getInstance(application)

    fun createDeviceInfo(vararg userInfo: UserInfo){
        viewModelScope.launch {
            userInfoRepository.createDeviceInfo(*userInfo)
        }
    }
}
