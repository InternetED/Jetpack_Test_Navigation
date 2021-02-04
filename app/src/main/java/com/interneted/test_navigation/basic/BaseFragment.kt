package com.interneted.test_navigation.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Creator: ED
 * Date: 2020/12/8 11:48 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    protected lateinit var viewDataBinding: VB

    private var isLoaded = false

    @LayoutRes
    abstract fun bindLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DataBindingUtil.inflate(inflater, bindLayoutId(), container, false)
        viewDataBinding.lifecycleOwner = this


        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLoaded && !isHidden) {
            isLoaded = true
            lazyInit()
        }
    }


    /**
     * 懶加載：初始化
     * */
    open fun lazyInit() {}


    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }
}