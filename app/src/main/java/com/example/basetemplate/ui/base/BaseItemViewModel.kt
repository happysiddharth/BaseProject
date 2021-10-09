package com.example.basetemplate.ui.base

import androidx.lifecycle.MutableLiveData
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T:Any>(
    networkHelper: NetworkHelper,
    compositeDisposable: CompositeDisposable,
):BaseViewModel(networkHelper,compositeDisposable) {
    val data = MutableLiveData<T>()

    fun onManualCleared() = onCleared()

    open fun updateData(data:T){
        this.data.postValue(data)
    }

}