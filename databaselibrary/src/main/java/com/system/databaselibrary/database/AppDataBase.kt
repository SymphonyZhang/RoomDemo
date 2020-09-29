package com.system.databaselibrary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.system.databaselibrary.database.dao.DeviceInfoDao
import com.system.databaselibrary.database.dao.GoodsInfoDao
import com.system.databaselibrary.database.entity.DeviceInfo
import com.system.databaselibrary.database.entity.GoodsInfo

@Database(entities = [GoodsInfo::class, DeviceInfo::class/*, UserInfo::class*/], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun deviceInfoDao(): DeviceInfoDao

    abstract fun goodsInfoDao(): GoodsInfoDao

    //abstract fun userInfoDao(): UserInfoDao

    companion object {

        private const val DB_NAME = "app.db"

        /*private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE deviceinfo (id INTEGER PRIMARY KEY NOT NULL,date_of_manufacture TEXT NOT NULL,device_no TEXT NOT NULL,date TEXT NOT NULL,order_count TEXT NOT NULL,calibrate_times INTEGER NOT NULL,init_ad TEXT NOT NULL)".trimIndent())
            }

        }*/
        /*private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE UserInfo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_no TEXT UNIQUE NOT NULL , user_name TEXT NOT NULL, user_password TEXT NOT NULL, user_tag INTEGER NOT NULL)")
                database.execSQL("CREATE UNIQUE INDEX index_UserInfo_user_no ON UserInfo ('user_no')")
                //CREATE UNIQUE INDEX IF NOT EXISTS `index_UserInfo_user_no` ON `${TABLE_NAME}` (`user_no`)
            }

        }*/

        //For Singleton instantiation
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)/*.addMigrations(
                MIGRATION_2_3
            )*/.build()
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