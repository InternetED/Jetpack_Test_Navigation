package com.interneted.test_navigation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.ui.second.SecondActivity

/**
 * Creator: ED
 * Date: 2020/12/8 11:50 AM
 * Mail: salahayo3192@gmail.com
 *
 * 負責測試切換至第二個 Activity，且指定頁面
 * **/
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

        startActivity(Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.BUNDLE_ENTER_TYPE, enterType)
        })
    }
}