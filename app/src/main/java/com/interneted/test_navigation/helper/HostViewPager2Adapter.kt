package com.interneted.test_navigation.helper

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.interneted.test_navigation.ui.second.host.inner.NumberFragment
import com.interneted.test_navigation.ui.second.host.inner.NumberListFragment

/**
 * Creator: ED
 * Date: 2/2/21 2:57 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class HostViewPager2Adapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val TAG = "HostViewPager2Adapter"

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "$position")

        if (position == 1){
            return NumberListFragment()
        }

        return NumberFragment().apply {
            arguments = bundleOf(NumberFragment.BUNDLE_NUMBER to "$position")
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d(TAG, "onAttachedToRecyclerView")
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Log.d(TAG, "onDetachedFromRecyclerView")
    }


}