package com.example.basetemplate.ui.gallery.images

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.basetemplate.ui.base.BaseItemViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageItemViewModel @Inject constructor(compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper) : BaseItemViewModel<Uri>(compositeDisposable = compositeDisposable,networkHelper = networkHelper ) {

    override fun onCreate() {

    }

    suspend fun sum(){

    }
}