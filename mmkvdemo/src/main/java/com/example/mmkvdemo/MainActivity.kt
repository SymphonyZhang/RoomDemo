package com.example.mmkvdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View){
        when(view){
            btn_1 -> {
                var manufactureDate = SpUtil.decodeString("manufactureDate")
                println("zyx ============>  manufactureDate    ===   $manufactureDate")
                if(null == manufactureDate || "" == manufactureDate){
                    SpUtil.encode("manufactureDate",getCurrentDate())
                }

                manufactureDate = SpUtil.decodeString("manufactureDate")
                println("zyx ************>  manufactureDate    ***   $manufactureDate")
            }
        }
    }

    /**
     * 获取当前日期
     */
    fun getCurrentDate(): String {
        var nowTime: Long = System.currentTimeMillis()
        var date = Date(nowTime)
        val sdf = SimpleDateFormat("yyy-MM-dd")
        return sdf.format(date)
    }
}