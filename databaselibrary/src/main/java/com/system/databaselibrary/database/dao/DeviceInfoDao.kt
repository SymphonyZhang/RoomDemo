package com.system.databaselibrary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.system.databaselibrary.database.entity.DeviceInfo

@Dao
interface DeviceInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg device: DeviceInfo)

    @Update
    suspend fun update(vararg device: DeviceInfo)

    @Query("SELECT * FROM DEVICEINFO")
    suspend fun getAllDeviceInfo():List<DeviceInfo>

    @Delete
    suspend fun deleteDeviceInfo(vararg device:DeviceInfo)

    @Query("SELECT CALIBRATE_TIMES FROM DEVICEINFO WHERE ID = 0")
    fun getCalibrateTimes(): LiveData<Int>

    @Query("UPDATE DEVICEINFO SET CALIBRATE_TIMES = CALIBRATE_TIMES+1 WHERE ID = 0")
    suspend fun updateCalibrateTimes()

    @Query("UPDATE DEVICEINFO SET ORDER_COUNT = ORDER_COUNT+1 WHERE ID = 0")
    suspend fun updateOrderCount()
}