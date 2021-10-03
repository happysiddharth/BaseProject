package com.example.basetemplate.di.component

import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.di.FragmentScope
import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.di.module.FragmentModule
import com.example.basetemplate.ui.dashboard.Dashboard
import dagger.Component

@FragmentScope
@Component(
    modules = [
        FragmentModule::class,
    ],
    dependencies = [
        ApplicationComponent::class,
    ]
)
interface FragmentComponent {
    fun inject(dashboard: Dashboard)
}