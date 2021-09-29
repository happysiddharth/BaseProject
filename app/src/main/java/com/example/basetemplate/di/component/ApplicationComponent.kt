package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.ApplicationModule
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
    fun getNetworkHelper(): NetworkHelper
}