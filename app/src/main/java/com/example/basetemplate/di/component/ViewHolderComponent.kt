package com.example.basetemplate.di.component

import com.example.basetemplate.di.module.ViewHolderModule
import com.example.basetemplate.di.ViewHolderScope
import com.example.basetemplate.ui.dashboard.users.UserViewHolder
import dagger.Component

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