package com.interneted.test_navigation.fragment

import androidx.navigation.fragment.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentOtherHostBinding

/**
 * Creator: ED
 * Date: 2020/12/8 11:44 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class OtherHostFragment : BaseFragment<FragmentOtherHostBinding>() {

    override fun bindLayoutId(): Int {
        return R.layout.fragment_other_host
    }

    override fun lazyInit() {
        super.lazyInit()
        viewDataBinding.btnGoHostPage.setOnClickListener {
            findNavController().navigate(OtherHostFragmentDirections.actionOtherHostFragmentToHostFragment())
        }

    }
}