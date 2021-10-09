package com.example.basetemplate.di.module

import android.app.Activity
import androidx.core.app.ActivityCompat
import com.example.basetemplate.MyApplication
import com.example.basetemplate.data.remote.NetworkService
import com.example.basetemplate.data.remote.Networking
import com.example.basetemplate.data.repository.SMSRepository
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.di.ApplicationScope
import com.example.basetemplate.di.Qualifiers
import com.example.basetemplate.util.common.Permissions
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(private val application:MyApplication) {

    @ApplicationScope
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @ApplicationScope
    fun apiKey():String = ""

    @ApplicationScope
    @Qualifiers
    fun dbName():String = ""


    @ApplicationScope
    @Provides
    fun permissions(): Permissions{
        return Permissions
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()







}