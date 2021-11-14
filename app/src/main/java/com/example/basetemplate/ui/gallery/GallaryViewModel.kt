package com.example.basetemplate.ui.gallery

import android.net.Uri
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.data.repository.MediaRepository
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GallaryViewModel(
    networkHelper: NetworkHelper,
    requireActivity: FragmentActivity,
    private val mediaRepository: MediaRepository,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel(networkHelper,compositeDisposable) {

    val uriList = MutableLiveData<ArrayList<Uri>>()
    override fun onCreate() {
        compositeDisposable.addAll(
            mediaRepository.getMedia()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list->
                    Log.e("TAG",Thread.currentThread().name)
                    uriList.postValue(list)
                },
                    {
                        it.printStackTrace()

                    }),
        )
    }

}