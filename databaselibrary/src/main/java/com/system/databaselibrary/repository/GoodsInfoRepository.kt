package com.system.databaselibrary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.system.databaselibrary.database.AppDataBase
import com.system.databaselibrary.database.dao.GoodsInfoDao
import com.system.databaselibrary.database.entity.GoodsInfo
import com.system.databaselibrary.util.E

class GoodsInfoRepository(context: Context) {
    private val goodsInfoDao: GoodsInfoDao = AppDataBase.getInstance(context).goodsInfoDao()

    var allGoodsInfo:LiveData<List<GoodsInfo>>

    companion object{
        @Volatile private var instant:GoodsInfoRepository? = null
        fun getInstance(context: Context)= instant ?: synchronized(this){
            instant ?: GoodsInfoRepository(context).also { instant = it }
        }
    }

    init {
        allGoodsInfo = goodsInfoDao.getAllGoodsInfo()
    }

    suspend fun createGoodsInfo(vararg goodsInfo:GoodsInfo){
        goodsInfoDao.insertGoodsInfo(*goodsInfo)
    }

    suspend fun updateGoodsInfo(vararg goodsInfo: GoodsInfo){
        goodsInfoDao.updateGoodsInfo(*goodsInfo)
    }

    suspend fun deleteGoodsInfo(vararg goodsInfo: GoodsInfo){
        goodsInfoDao.deleteGoodsInfo(*goodsInfo)
    }

    suspend fun getGoodsInfoByHotKey(department:String,hotKey:Int):GoodsInfo{
        E("currentThread getGoodsInfoByHotKey  =====>    ${Thread.currentThread().name}")
        return goodsInfoDao.getGoodsInfoByHotKey(department,hotKey)
    }

    suspend fun getGoodsInfoByCode(goodsCode:String):GoodsInfo{
        E("currentThread getGoodsInfoByCode  =====>    ${Thread.currentThread().name}")
        return goodsInfoDao.getGoodsInfoByGoodsCode(goodsCode)
    }

    suspend fun getGoodsInfoByBarcode(barcode:String):GoodsInfo{
        E("currentThread  getGoodsInfoByBarcode =====>    ${Thread.currentThread().name}")
        return goodsInfoDao.getGoodsInfoByBarcode(barcode)
    }

}