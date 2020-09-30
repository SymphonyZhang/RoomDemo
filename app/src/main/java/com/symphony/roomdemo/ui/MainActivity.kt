package com.symphony.roomdemo.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.symphony.roomdemo.I
import com.symphony.roomdemo.R
import com.system.databaselibrary.database.entity.GoodsInfo
import com.system.databaselibrary.database.entity.UserInfo
import com.system.databaselibrary.util.D
import com.system.databaselibrary.util.E
import com.system.databaselibrary.viewmodel.DeviceInfoViewModel
import com.system.databaselibrary.viewmodel.GoodsInfoViewModel
import com.system.databaselibrary.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val deviceInfoViewModel: DeviceInfoViewModel by viewModels()
    private val goodsInfoViewModel: GoodsInfoViewModel by viewModels()
    private val userInfoViewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*deviceInfoViewModel.calibrateTimes.observe(this, Observer {
            tv_count.text = it?.toString() ?: "0"
            println("zyx  *******************************  it    :    $it   ************************************")
        })*/
        goodsInfoViewModel.allGoodsInfo.observe(this, Observer {
            tv_count.text = it?.toString() ?: "null"
            I(it.toString())
        })
    }


    fun click(view: View) {
        when (view) {
            btn_insert -> {
                //for (i in 0..10){
                //deviceInfoViewModel.createDeviceInfo(DeviceInfo())
                //}
                /*val list = arrayOf(
                    GoodsInfo(
                        goodsCode = "001",
                        goodsName = "苹果",
                        price = 5.000f,
                        hotKeyGroup = "D0",
                        hotKey = KeyEvent.KEYCODE_Q,
                        stock = 100.0f
                    ), GoodsInfo(
                        goodsCode = "002",
                        goodsName = "橙子",
                        price = 6.500f,
                        hotKeyGroup = "D0",
                        hotKey = KeyEvent.KEYCODE_W,
                        stock = 100.0f
                    ), GoodsInfo(
                        goodsCode = "003",
                        goodsName = "菠萝",
                        price = 5.500f,
                        hotKeyGroup = "D0",
                        hotKey = KeyEvent.KEYCODE_E,
                        stock = 100.0f
                    ), GoodsInfo(
                        goodsCode = "004",
                        goodsName = "卷心菜",
                        price = 3.500f,
                        hotKeyGroup = "D0",
                        hotKey = KeyEvent.KEYCODE_R,
                        stock = 100.0f
                    ), GoodsInfo(
                        goodsCode = "007",
                        goodsName = "美汁源 450ml",
                        price = 6.000f,
                        hotKeyGroup = "D0",
                        hotKey = KeyEvent.KEYCODE_U,
                        goodsType = TYPE_COUNT,
                        ean_13 = "6956416200067",
                        stock = 100.0f
                    )
                )
                goodsInfoViewModel.createGoodsInfo(list[0], list[1], list[2], list[3], list[4])*/
                /*val deviceInfo = DeviceInfo()
                lifecycleScope.launch {
                    deviceInfoViewModel.createDeviceInfo(deviceInfo)
                }*/
                val list = arrayOf(
                    UserInfo("001","Jim","123456",true),
                    UserInfo("002","Tom","456789",false)
                )
                lifecycleScope.launch {
                    userInfoViewModel.createDeviceInfo(list[0],list[1])
                }
            }
            btn_update -> {
                //deviceInfoViewModel.updateDeviceInfo(DeviceInfo(orderCount = "100"))
                //deviceInfoViewModel.updateOrderCount()

                lifecycleScope.launch {
                    val goodsInfoByCode = async { goodsInfoViewModel.getGoodsInfoByCode("009") }
                    val goodsInfoByBarcode =
                        async { goodsInfoViewModel.getGoodsInfoByBarcode("1234564235779") }
                    when {
                        goodsInfoByCode.await() != null -> {
                            I("商品根据Code来的")
                        }
                        goodsInfoByBarcode.await() != null -> {
                            I("商品根据条形码来的")
                        }
                        else -> {
                            I("找不到商品")
                        }
                    }
                }
            }
            btn_query -> {
                //deviceInfoViewModel.getAllDeviceInfo()
                //goodsInfoViewModel.getGoodsInfoByCode("004")
                /*lifecycleScope.launch {
                    val goodsInfo = async { goodsInfoViewModel.getGoodsInfoByCode("007")}
                    E(goodsInfo.await().toString())
                }*/
                /*goodsInfoViewModel.createGoodsInfo(
                    GoodsInfo(
                        goodsCode = "003",
                        goodsName = "菠萝",
                        price = 5.500f,
                        hotKey = KeyEvent.KEYCODE_E,
                        stock = 100.0f
                    )
                )*/

            }
            btn_delete -> {
                //deviceInfoViewModel.deleteDeviceInfo(DeviceInfo(id = 5))
                /*try {
                    goodsInfoViewModel.createGoodsInfo(
                        GoodsInfo(
                            goodsCode = "003",
                            goodsName = "菠萝",
                            price = 5.500f,
                            hotKey = KeyEvent.KEYCODE_E,
                            ean_13 = "7894567894569",
                            stock = 100.0f
                        )
                    )
                } catch (e: Exception) {

                }*/
                var goods = GoodsInfo(
                    goodsCode = "004",
                    goodsName = "卷心菜",
                    price = 3.500f,
                    hotKeyGroup = "D0",
                    hotKey = KeyEvent.KEYCODE_R,
                    stock = 100.0f
                )
                goods.id = 4
                goodsInfoViewModel.deleteGoodsInfo(
                    goods
                )

            }
            btn_update_count -> {
                //deviceInfoViewModel.updateCalibrateTimes()
                goodsInfoViewModel.createGoodsInfo(
                    GoodsInfo(
                        goodsCode = "008",
                        goodsName = "菠萝",
                        price = 5.500f,
                        hotKey = KeyEvent.KEYCODE_E,
                        ean_13 = "-e93518021f9a",
                        stock = 100.0f
                    )
                )
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && event.action == KeyEvent.ACTION_UP) {
            lifecycleScope.launch {
                val goodsInfo = async { goodsInfoViewModel.getGoodsInfoByCode("004") }
                if (null == goodsInfo.await()) {
                    D("没有这个编码的商品")
                } else {
                    E(goodsInfo.await().toString())
                }
            }
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP && event.action == KeyEvent.ACTION_UP) {
            lifecycleScope.launch {
                val goodsInfo = async { goodsInfoViewModel.getGoodsInfoByBarcode("6956416200067") }
                if (null == goodsInfo.await()) {
                    D("没有这个条码的商品")
                } else {
                    E(goodsInfo.await().toString())
                }
            }
        }
        if (keyCode == KeyEvent.KEYCODE_W && event.action == KeyEvent.ACTION_UP) {
            lifecycleScope.launch {
                val goodsInfo =
                    async { goodsInfoViewModel.getGoodsInfoByHotKey("D0", keyCode) }
                if (null == goodsInfo.await()) {
                    D("这个热键组合没有商品")
                } else {
                    E(goodsInfo.await().toString())
                }
            }
        }
        if (keyCode == KeyEvent.KEYCODE_G && event.action == KeyEvent.ACTION_UP) {
            lifecycleScope.launch {
                val goodsInfo =
                    async { goodsInfoViewModel.getGoodsInfoByHotKey("D0", keyCode) }
                if (null == goodsInfo.await()) {
                    D("这个热键组合没有商品")
                } else {
                    E(goodsInfo.await().toString())
                }
            }
        }
        if (keyCode == KeyEvent.KEYCODE_Q && event.action == KeyEvent.ACTION_UP) {
            lifecycleScope.launch {
                val goodsInfo =
                    async { goodsInfoViewModel.getGoodsInfoByHotKey("D1", keyCode) }
                if (null == goodsInfo.await()) {
                    D("这个热键组合没有商品")
                } else {
                    E(goodsInfo.await().toString())
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }
}