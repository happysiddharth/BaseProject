package com.example.basetemplate.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basetemplate.repo.UsersRepository
import com.example.basetemplate.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper

class HomeViewModel(networkHelper: NetworkHelper,val usersRepository: UsersRepository)
    : BaseViewModel(networkHelper) {


    private val _title = MutableLiveData<String>()
    val title:LiveData<String> get() = _title

    fun changeTitle(message:String){
        _title.value = (message)
    }

    private val _userList = MutableLiveData<ArrayList<String>>()
    val userList:LiveData<ArrayList<String>> get() = _userList

    fun fetUsers(){
        _userList.postValue(usersRepository.fetUser())
    }

    override fun onCreate() {

    }


}