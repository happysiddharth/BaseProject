package com.example.basetemplate.di.component

import com.example.basetemplate.di.FragmentScope
import com.example.basetemplate.di.module.FragmentModule
import com.example.basetemplate.ui.sms.Sms
import com.example.basetemplate.ui.gallery.Gallery
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
    fun inject(sms: Sms)
    fun inject(gallery: Gallery)
}