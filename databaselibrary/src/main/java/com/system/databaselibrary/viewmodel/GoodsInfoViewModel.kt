package com.system.databaselibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.system.databaselibrary.database.entity.GoodsInfo
import com.system.databaselibrary.repository.GoodsInfoRepository
import com.system.databaselibrary.util.D
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GoodsInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val goodsInfoRepository = GoodsInfoRepository.getInstance(application)

    /**
     * 观察模式
     * 获取所有的商品信息
     */
    val allGoodsInfo: LiveData<List<GoodsInfo>>
        get() = goodsInfoRepository.allGoodsInfo

    //val goodsInfoByCode:LiveData<GoodsInfo>
    /**
     * 插入商品信息
     */
    fun createGoodsInfo(vararg goodsInfo: GoodsInfo) {
        viewModelScope.launch {
            goodsInfoRepository.createGoodsInfo(*goodsInfo)
        }
    }

    /**
     * 更新产品信息
     */
    fun updateGoodsInfo(vararg goodsInfo: GoodsInfo) {
        viewModelScope.launch {
            goodsInfoRepository.updateGoodsInfo(*goodsInfo)
        }
    }

    /**
     * 删除产品信息
     */
    fun deleteGoodsInfo(vararg goodsInfo: GoodsInfo) {
        viewModelScope.launch {
            goodsInfoRepository.deleteGoodsInfo(*goodsInfo)
        }
    }

    suspend fun getGoodsInfoByHotKey(department: String, hotkey: Int):GoodsInfo = withContext(Dispatchers.IO){
        D("===========================================>   department: $department     hotkey: $hotkey   ")
        goodsInfoRepository.getGoodsInfoByHotKey(department,hotkey)
    }

    suspend fun getGoodsInfoByCode(goodsCode: String): GoodsInfo = withContext(Dispatchers.IO) {
        D("===========================================>   goodsCode: $goodsCode   ")
        goodsInfoRepository.getGoodsInfoByCode(goodsCode)
    }

    suspend fun getGoodsInfoByBarcode(barcode: String): GoodsInfo = withContext(Dispatchers.IO) {
        D("===========================================>   barcode: $barcode   ")
        goodsInfoRepository.getGoodsInfoByBarcode(barcode)
    }
}