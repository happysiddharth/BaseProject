package com.example.basetemplate.ui.dashboard.users

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basetemplate.MyApplication
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.base.BaseItemViewModel
import com.example.basetemplate.ui.home.BottomSheet
import com.example.basetemplate.ui.home.HomeActivity
import com.example.basetemplate.util.common.Resource
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserItemViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    compositeDisposable: CompositeDisposable
) : BaseItemViewModel<SMS>(networkHelper,compositeDisposable) {

    val bottomSheet:MutableLiveData<Resource<SMS>> = MutableLiveData()
    override fun onCreate() {

    }

    public fun changeDataInList(){
    }

    public fun openBottomSheet(sms: SMS){
        bottomSheet.postValue(Resource.success(sms))

    }



}