package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.di.module.ApplicationModule
import com.example.basetemplate.ui.home.HomeActivity
import dagger.Component

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
}