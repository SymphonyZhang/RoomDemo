package com.system.databaselibrary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.system.databaselibrary.database.dao.DeviceInfoDao
import com.system.databaselibrary.database.dao.GoodsInfoDao
import com.system.databaselibrary.database.entity.DeviceInfo
import com.system.databaselibrary.database.entity.GoodsInfo

@Database(entities = [DeviceInfo::class,GoodsInfo::class],version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun deviceInfoDao(): DeviceInfoDao

    abstract fun goodsInfoDao(): GoodsInfoDao

    companion object{

        private const val DB_NAME = "app.db"

        //For Singleton instantiation
        @Volatile private var INSTANCE:AppDataBase? = null

        fun getInstance(context: Context): AppDataBase  = INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context.applicationContext).also{ INSTANCE = it}
            }

        private fun buildDatabase(context: Context):AppDataBase{
            return Room.databaseBuilder(context,AppDataBase::class.java, DB_NAME).build()
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        /*private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }*/
    }
}