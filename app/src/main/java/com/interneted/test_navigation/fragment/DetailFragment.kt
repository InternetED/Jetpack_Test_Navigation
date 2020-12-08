package com.interneted.test_navigation.fragment

import androidx.navigation.fragment.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentDetailBinding

/**
 * Creator: ED
 * Date: 2020/12/8 11:54 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun bindLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun lazyInit() {
        super.lazyInit()

        viewDataBinding.btnGoHostPage.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHostFragment2())
        }
    }
}