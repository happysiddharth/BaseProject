package com.example.basetemplate.ui.base

import androidx.lifecycle.MutableLiveData
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper

abstract class BaseItemViewModel<T:Any>(
    networkHelper: NetworkHelper
):BaseViewModel(networkHelper) {
    val data = MutableLiveData<T>()

    fun onManualCleared() = onCleared()

    open fun updateData(data:T){
        this.data.postValue(data)
    }

}