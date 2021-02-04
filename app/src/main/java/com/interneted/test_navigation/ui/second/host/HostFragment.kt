package com.interneted.test_navigation.ui.second.host

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentHostBinding
import com.interneted.test_navigation.helper.FixFragmentNavigator
import com.interneted.test_navigation.helper.HostViewPager2Adapter

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


            val saveFragmentInstanceState =
                parentFragmentManager.saveFragmentInstanceState(this)

            // 前往詳情頁面
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(FixFragmentNavigator.BUNDLE_STATE to saveFragmentInstanceState)
            )
        }


        with(viewDataBinding) {
            viewPager2.adapter = HostViewPager2Adapter(this@HostFragment)
            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigationView.menu.getItem(position).isChecked = true
                }
            })


            // 當 ViewPager 切換頁面時，改變 ViewPager 的顯示
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> viewPager2.setCurrentItem(0, true)
                    R.id.navigation_collections -> viewPager2.setCurrentItem(1, true)
                    R.id.navigation_person -> viewPager2.setCurrentItem(2, true)
                }
                true
            }
        }

    }
}