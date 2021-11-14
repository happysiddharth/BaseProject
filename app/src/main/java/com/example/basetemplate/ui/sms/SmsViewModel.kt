package com.example.basetemplate.ui.sms

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.repository.SMSRepository
import com.example.basetemplate.ui.base.BaseViewModel
import com.example.basetemplate.util.common.Resource
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class SmsViewModel(
    networkHelper: NetworkHelper,
    private val activity: Activity,
    private val smsRepository: SMSRepository,
    private val compositeDisposable: CompositeDisposable,
) : BaseViewModel(
    networkHelper,
    compositeDisposable
) {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data
    override fun onCreate() {
        initSearchSMS()
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _colName: MutableLiveData<ArrayList<SMS>> =
        MutableLiveData<ArrayList<SMS>>().apply {
            value = ArrayList()
        }

    val colName: LiveData<ArrayList<SMS>> get() = _colName

     fun readSMS() {
        _loading.postValue(true)
        compositeDisposable.addAll(
            smsRepository.readSMS()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { list ->
                        _loading.postValue(false)
                        _colName.postValue(list)
                    }, { throwable ->
                        messageString.postValue(Resource.error("Something went wrong"))
                        throwable.printStackTrace()
                        _loading.postValue(false)
                    }
                )
        )
    }

    private val searchObservable = PublishSubject.create<String>()

    fun initSearchSMS() {
        compositeDisposable.addAll(
            searchObservable
                .subscribeOn(Schedulers.io())
                .debounce(400, TimeUnit.MILLISECONDS)
                .skip(1)
                .distinctUntilChanged()
                .switchMap { query ->
                    smsRepository.searchSMS(query)
                        .subscribeOn(Schedulers.io())
                }
                .subscribe({ list ->
                    _colName.value?.clear()
                    _colName.postValue(list)

                }, { e ->
                    e.printStackTrace()
                })
        )
    }

    fun searchSms(searchString: String) {
        searchObservable.onNext(searchString)
    }
}