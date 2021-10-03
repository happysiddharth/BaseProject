package com.example.basetemplate.ui.dashboard.users

import androidx.lifecycle.viewModelScope
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.ui.base.BaseItemViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserItemViewModel @Inject constructor(
    networkHelper: NetworkHelper
) : BaseItemViewModel<SMS>(networkHelper) {
    override fun onCreate() {
    }

    public fun changeDataInList(){

    }



}