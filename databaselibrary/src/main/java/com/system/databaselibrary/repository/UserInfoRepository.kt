package com.system.databaselibrary.repository

import android.content.Context
import com.system.databaselibrary.database.AppDataBase
import com.system.databaselibrary.database.dao.UserInfoDao
import com.system.databaselibrary.database.entity.UserInfo

class UserInfoRepository(context: Context) {

    private val userInfoDao: UserInfoDao = AppDataBase.getInstance(context).userInfoDao()


    companion object{
        @Volatile private var instant:UserInfoRepository? = null
        fun getInstance(context: Context)= instant ?: synchronized(this){
            instant ?: UserInfoRepository(context).also { instant = it }
        }
    }

    suspend fun createDeviceInfo(vararg userInfo: UserInfo) {
        userInfoDao.createUser(*userInfo)
    }
}
