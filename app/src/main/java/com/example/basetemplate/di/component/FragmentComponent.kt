package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.FragmentModule
import dagger.Component

@Component(
    modules = [
        FragmentModule::class
    ]
)
interface FragmentComponent {
}