package com.example.basetemplate

import android.app.Application
import com.example.basetemplate.di.component.ApplicationComponent
import com.example.basetemplate.di.component.DaggerApplicationComponent
import com.example.basetemplate.di.module.ApplicationModule

class MyApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}