package com.example.basetemplate.di.component

import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.di.module.ApplicationModule
import com.example.basetemplate.ui.home.HomeActivity
import com.example.basetemplate.ui.home.HomeViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Component

@ActivityScope
@Component(
    modules = [
        ActivityModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ActivityComponent {
    fun inject(homeActivity:HomeActivity)
    fun getHomeComponent(): HomeViewModel
    fun getNetworkHelper(): NetworkHelper
}