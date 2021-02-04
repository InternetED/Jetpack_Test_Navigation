package com.interneted.test_navigation.ui.second.host.inner

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.interneted.test_navigation.R
import com.interneted.test_navigation.basic.BaseFragment
import com.interneted.test_navigation.databinding.FragmentNumberBinding
import com.interneted.test_navigation.ui.second.host.HostViewModel

/**
 * Creator: ED
 * Date: 2/2/21 3:07 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class NumberFragment : BaseFragment<FragmentNumberBinding>(), View.OnClickListener {

    companion object {
        const val BUNDLE_NUMBER = "BUNDLE_NUMBER"

    }

    private val mViewModel by lazy {
        getNumberViewModel()
    }


    override fun bindLayoutId(): Int {
        return R.layout.fragment_number
    }

    override fun lazyInit() {
        super.lazyInit()



        mViewModel.numberLiveData.observe(this, {
            viewDataBinding.tvNumber.text = it.toString()
        })


        viewDataBinding.btnAddNumber.setOnClickListener(this)
        viewDataBinding.btnReduceNumber.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btnAddNumber -> addNumber()
            R.id.btnReduceNumber -> reduceNumber()

        }
    }

    private fun addNumber() {
        mViewModel.addNumber()
    }


    private fun reduceNumber() {
        mViewModel.reduceNumber()
    }


    private fun getNumberViewModel(): NumberViewModel {

        /**
         * ViewModel Store 由 NavController 控管
         * */
        fun getHostViewModelStore(): ViewModelStoreOwner {
            return findNavController().getViewModelStoreOwner(R.id.nav_host)
        }


        // 此為 NavGraph 中單一實例
        val hostViewModel =
            ViewModelProvider(getHostViewModelStore()).get(HostViewModel::class.java)

        val bundleNumber = arguments?.getString(BUNDLE_NUMBER)!!


        val viewModelProvider = ViewModelProvider(
            hostViewModel.listViewModelStore,
            ViewModelProvider.NewInstanceFactory()
        )
        return viewModelProvider.get(bundleNumber, NumberViewModel::class.java)
    }
}