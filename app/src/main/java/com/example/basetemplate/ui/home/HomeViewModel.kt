package com.example.basetemplate.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(networkHelper: NetworkHelper,
                    val usersRepository: UsersRepository,
                    compositeDisposable: CompositeDisposable)
    : BaseViewModel(networkHelper,compositeDisposable) {


    private val _title = MutableLiveData<String>()
    val title:LiveData<String> get() = _title

    fun changeTitle(message:String){
        _title.value = (message)
    }

    private val _userList = MutableLiveData<ArrayList<Users>>()
    val userList:LiveData<ArrayList<Users>> get() = _userList

    fun fetUsers(){
        _userList.postValue(usersRepository.fetUser())
    }

    override fun onCreate() {

    }


}