package com.interneted.test_navigation.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.interneted.test_navigation.helper.FixFragmentNavigator

/**
 * Creator: ED
 * Date: 2020/12/8 2:19 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/

class WindowNavHostFragment : NavHostFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val frameLayout = WindowFrameLayout(inflater.context)
        frameLayout.id = id
        return frameLayout
    }

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)

        val fixFragmentNavigator = FixFragmentNavigator(requireContext(), childFragmentManager, id)
        navController.navigatorProvider.addNavigator(fixFragmentNavigator)
    }

}