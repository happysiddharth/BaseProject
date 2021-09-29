package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.ViewHolderModule
import com.example.basetemplate.di.scope.ViewHolderScope
import com.example.basetemplate.ui.dashboard.UserItemViewModel
import com.example.basetemplate.ui.dashboard.UserViewHolder
import dagger.Component
import dagger.Module

@ViewHolderScope
@Component(
    modules = [
        ViewHolderModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ViewHolderComponent {
    fun inject(userItemViewModel: UserViewHolder)
}