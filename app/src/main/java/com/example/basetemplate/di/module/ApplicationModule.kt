package com.example.basetemplate.di.module

import com.example.basetemplate.MyApplication
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application:MyApplication) {

    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}