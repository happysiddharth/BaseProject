package com.example.basetemplate.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper

class DashboardViewModel(networkHelper: NetworkHelper):BaseViewModel(
    networkHelper
) {
    private val _data = MutableLiveData<String>()
    val data:LiveData<String> get() = _data
    override fun onCreate() {
        _data.postValue("siddharth")
    }
}