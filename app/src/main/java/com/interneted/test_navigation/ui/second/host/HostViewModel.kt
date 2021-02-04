package com.interneted.test_navigation.ui.second.host

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore

/**
 * Creator: ED
 * Date: 2/3/21 9:41 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class HostViewModel : ViewModel() {

     // 負責儲存列表中 Fragment ViewModelStore
     val listViewModelStore = ViewModelStore()
}