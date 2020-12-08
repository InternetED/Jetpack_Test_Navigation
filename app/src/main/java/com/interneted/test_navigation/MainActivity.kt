package com.interneted.test_navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnGoDetailPage).setOnClickListener(this)
        findViewById<Button>(R.id.btnGoHostPage).setOnClickListener(this)
        findViewById<Button>(R.id.btnGoOtherHostPage).setOnClickListener(this)
    }

    override fun onClick(view: View) {

        val enterType = when (view.id) {
            // 前往詳情頁面
            R.id.btnGoDetailPage -> {
                SecondActivity.ENTER_TYPE_DETAIL
            }
            // 前往首頁頁面
            R.id.btnGoHostPage -> {
                SecondActivity.ENTER_TYPE_HOST
            }

            // 前往其他首頁頁面
            R.id.btnGoOtherHostPage -> {
                SecondActivity.ENTER_TYPE_OTHER_HOST
            }
            else -> throw IllegalAccessError("尚未定義")
        }
        Log.d("asdmaod", "前往第二頁 $enterType")

        startActivity(Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.BUNDLE_ENTER_TYPE, enterType)
        })
    }
}