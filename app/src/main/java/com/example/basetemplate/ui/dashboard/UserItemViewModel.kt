package com.example.basetemplate.ui.dashboard

import com.example.basetemplate.ui.base.BaseItemViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import javax.inject.Inject

class UserItemViewModel<T:Any> @Inject constructor(
    networkHelper: NetworkHelper
) : BaseItemViewModel<T>(networkHelper) {
    override fun onCreate() {

    }
}