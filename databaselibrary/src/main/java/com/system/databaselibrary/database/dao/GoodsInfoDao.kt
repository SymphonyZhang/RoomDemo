package com.system.databaselibrary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.system.databaselibrary.database.entity.GoodsInfo

@Dao
interface GoodsInfoDao {
    /**
     * 添加新的商品
     */
    @Insert
    suspend fun insertGoodsInfo(vararg goodsInfo: GoodsInfo)

    /**
     * 更新商品信息
     */
    @Update
    suspend fun updateGoodsInfo(vararg goodsInfo: GoodsInfo)

    @Delete
    suspend fun deleteGoodsInfo(vararg goodsInfo: GoodsInfo)

    /**
     * 根据热键获取商品信息
     */
    @Query("SELECT * FROM GOODSINFO WHERE HOT_KEY_GROUP = :department AND HOT_KEY = :hotkey")
    suspend fun getGoodsInfoByHotKey(department: String, hotkey: Int): GoodsInfo

    /**
     * 根据商品编号获取商品信息
     */
    @Query("SELECT * FROM GOODSINFO WHERE GOODS_CODE LIKE :goodsCode")
    suspend fun getGoodsInfoByGoodsCode(goodsCode: String): GoodsInfo

    /**
     * 根据条形码获取商品信息
     */
    @Query("SELECT * FROM GOODSINFO WHERE BARCODE = :barcode")
    suspend fun getGoodsInfoByBarcode(barcode: String): GoodsInfo

    /**
     * 获取全部商品信息
     */
    @Query("SELECT * FROM GOODSINFO")
    fun getAllGoodsInfo(): LiveData<List<GoodsInfo>>
}