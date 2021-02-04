package com.interneted.test_navigation.ui.second

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.interneted.test_navigation.R


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

        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {

            private val TAG = "SecondLifecycleCallback"

            override fun onFragmentCreated(
                fm: FragmentManager,
                f: Fragment,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentCreated(fm, f, savedInstanceState)
                Log.d(TAG, "onFragmentCreated:$f")
            }

            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentDestroyed(fm, f)
                Log.d(TAG, "onFragmentDestroyed:$f")
            }
        }, true)


        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)


        val startDestination =
            when (intent.getStringExtra(BUNDLE_ENTER_TYPE)) {
                ENTER_TYPE_HOST -> R.id.hostFragment
                ENTER_TYPE_DETAIL -> R.id.detailFragment
                ENTER_TYPE_OTHER_HOST -> R.id.otherHostFragment
                else -> throw IllegalAccessError("")
            }

        val navGraph = navController.navInflater.inflate(R.navigation.nav_second_host)
        navGraph.startDestination = startDestination

        navController.graph = navGraph


    }
}