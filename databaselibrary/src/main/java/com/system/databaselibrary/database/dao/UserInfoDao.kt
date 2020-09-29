package com.system.databaselibrary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.system.databaselibrary.database.entity.UserInfo



@Dao
interface UserInfoDao {
    @Insert
    suspend fun createUser(vararg userInfo: UserInfo)
}
