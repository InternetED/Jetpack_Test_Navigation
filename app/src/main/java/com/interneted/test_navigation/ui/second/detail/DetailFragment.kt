package com.interneted.test_navigation.ui.second.detail

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentDetailBinding
import com.interneted.test_navigation.helper.FixFragmentNavigator
import com.interneted.test_navigation.helper.navigateWithRestoreState
import com.interneted.test_navigation.ui.second.host.HostFragment

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

        val savedState = arguments?.getParcelable<SavedState>(FixFragmentNavigator.BUNDLE_STATE)

        val newInstance = HostFragment::class.java.newInstance()
        newInstance.setInitialSavedState(savedState)

        childFragmentManager.beginTransaction()
            .add(R.id.flContainer, newInstance)
            .commit()


        viewDataBinding.btnGoHostPage.setOnClickListener {
            backToHostFragmentWithState(newInstance)
        }




        requireActivity().onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToHostFragmentWithState(newInstance)
            }

        });

    }

    private fun backToHostFragmentWithState(newInstance: HostFragment) {
        val saveFragmentInstanceState =
            childFragmentManager.saveFragmentInstanceState(newInstance)

        if (saveFragmentInstanceState != null) {
            findNavController().navigateWithRestoreState(
                R.id.hostFragment,
                saveFragmentInstanceState
            )
        }
    }

}