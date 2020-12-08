package com.interneted.test_navigation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController

/**
 * Creator: ED
 * Date: 2020/12/8 11:58 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class SecondActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_ENTER_TYPE = "BUNDLE_ENTER_TYPE"

        const val ENTER_TYPE_DETAIL = "DETAIL"
        const val ENTER_TYPE_HOST = "HOST"
        const val ENTER_TYPE_OTHER_HOST = "OTHER_HOST"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)


        val startDestination =
            when (intent.getStringExtra(BUNDLE_ENTER_TYPE)) {
                ENTER_TYPE_HOST -> R.id.hostFragment
                ENTER_TYPE_DETAIL -> R.id.detailFragment
                ENTER_TYPE_OTHER_HOST -> R.id.otherHostFragment
                else -> throw IllegalAccessError("")
            }

        navController.navigate(
            startDestination, null, NavOptions.Builder()
                .setPopUpTo(R.id.hostFragment, true).build()
        )


    }
}