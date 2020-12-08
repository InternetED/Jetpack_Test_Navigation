package com.interneted.test_navigation.fragment

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentHostBinding

/**
 * Creator: ED
 * Date: 2020/12/8 11:44 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class HostFragment : BaseFragment<FragmentHostBinding>() {

    override fun bindLayoutId(): Int {
        return R.layout.fragment_host
    }

    override fun lazyInit() {
        super.lazyInit()

        viewDataBinding.btnGoDetailPage.setOnClickListener {
            // 前往詳情頁面
            findNavController().navigate(HostFragmentDirections.actionHostFragmentToDetailFragment())

        }

    }
}