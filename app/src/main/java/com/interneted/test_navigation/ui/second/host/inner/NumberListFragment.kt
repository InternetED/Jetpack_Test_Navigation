package com.interneted.test_navigation.ui.second.host.inner

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentNumberListBinding
import com.interneted.test_navigation.helper.NumberListAdapter


/**
 * Creator: ED
 * Date: 2/3/21 6:43 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class NumberListFragment : BaseFragment<FragmentNumberListBinding>() {


    companion object {
        const val BUNDLE_RECYCLER_LAYOUT = "BUNDLE_RECYCLER_LAYOUT"
    }

    override fun bindLayoutId(): Int {
        return R.layout.fragment_number_list
    }

    override fun lazyInit() {
        super.lazyInit()
        viewDataBinding.rvNumberList.layoutManager = LinearLayoutManager(requireContext())
        viewDataBinding.rvNumberList.adapter = NumberListAdapter()
    }

    /**
     * This is a method for Fragment.
     * You can do the same in onCreate or onRestoreInstanceState
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState =
                savedInstanceState.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
            viewDataBinding.rvNumberList.layoutManager?.onRestoreInstanceState(
                savedRecyclerLayoutState
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(
            BUNDLE_RECYCLER_LAYOUT,
            viewDataBinding.rvNumberList.layoutManager?.onSaveInstanceState()
        )
    }
}