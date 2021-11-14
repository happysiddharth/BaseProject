package com.example.basetemplate.di.component

import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.main.HomeActivity
import com.example.basetemplate.ui.main.HomeViewModel
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
    fun getActivity(): BaseActivity<*>
}