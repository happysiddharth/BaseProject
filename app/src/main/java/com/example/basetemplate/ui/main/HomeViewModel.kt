package com.example.basetemplate.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.ui.base.BaseViewModel
import com.example.basetemplate.util.common.Event
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel (networkHelper: NetworkHelper,
                    val compositeDisposable: CompositeDisposable)
    : BaseViewModel(networkHelper,compositeDisposable) {


    val gallery = MutableLiveData<Event<Boolean>>()
    val sms = MutableLiveData<Event<Boolean>>()

    private val _title = MutableLiveData<String>()
    val title:LiveData<String> get() = _title

    fun changeTitle(message:String){
        _title.value = (message)
    }

    private val _userList = MutableLiveData<ArrayList<Users>>()
    val userList:LiveData<ArrayList<Users>> get() = _userList

    fun fetUsers(){
    }

    override fun onCreate() {
        sms.postValue(Event(true))
    }

    fun onSmsSelected() {
        sms.postValue(Event(true))
    }

    fun onGallerySelected() {
        gallery.postValue(Event(true))
    }


}