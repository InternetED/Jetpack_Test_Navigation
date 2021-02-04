package com.interneted.test_navigation.ui.second.host.inner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Creator: ED
 * Date: 2/3/21 5:50 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class NumberViewModel : ViewModel() {
    private val _numberLiveData = MutableLiveData(0)

    val numberLiveData: LiveData<Int> = _numberLiveData


    fun addNumber() {

        val number = _numberLiveData.value ?: 0

        _numberLiveData.value = number + 1
    }


    fun reduceNumber() {
        val number = _numberLiveData.value ?: 0

        _numberLiveData.value = number - 1
    }

}