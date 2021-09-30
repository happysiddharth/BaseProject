package com.example.basetemplate.ui.dashboard

import androidx.lifecycle.viewModelScope
import com.example.basetemplate.ui.base.BaseItemViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserItemViewModel<T:Any> @Inject constructor(
    networkHelper: NetworkHelper
) : BaseItemViewModel<T>(networkHelper) {
    override fun onCreate() {

    }

    override fun updateData(data: T) {
        viewModelScope.launch {
            delay(2000)
            super.updateData(data)
        }
    }
}