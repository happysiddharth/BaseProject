package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.FragmentModule
import com.example.basetemplate.ui.dashboard.Dashboard
import dagger.Component

@Component(
    modules = [
        FragmentModule::class
    ]
)
interface FragmentComponent {
    fun inject(dashboard: Dashboard)
}