package com.example.basetemplate.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.basetemplate.di.scope.ViewHolderScope
import com.example.basetemplate.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(
    private val viewHolder:BaseItemViewHolder<*,*>
) {
    @Provides
    @ViewHolderScope
    fun provideLifeCycleRegistry() = LifecycleRegistry(viewHolder)
}