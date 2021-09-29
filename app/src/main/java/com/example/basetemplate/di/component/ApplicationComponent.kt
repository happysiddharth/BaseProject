package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.ApplicationModule
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
}