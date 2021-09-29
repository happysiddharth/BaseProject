package com.example.basetemplate.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper

abstract class BaseViewModel(
    private val networkHelper: NetworkHelper
):ViewModel() {

    val messageStringId = MutableLiveData<Int>()
    val messageString = MutableLiveData<String>()

    protected fun checkInternetConnection():Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err:Throwable){
    }

    override fun onCleared() {
        super.onCleared()
    }

    abstract fun onCreate()
}