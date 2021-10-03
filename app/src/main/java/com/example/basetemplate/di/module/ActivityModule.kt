package com.example.basetemplate.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.ViewModelFactory
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity:BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @ActivityScope
    @Provides
    fun providesHomeViewModel(
        networkHelper: NetworkHelper,
        usersRepository: UsersRepository
    ):HomeViewModel{
        return ViewModelProviders.of(activity,
            ViewModelFactory(
                HomeViewModel::class
            ) {
                HomeViewModel(networkHelper,usersRepository )
            }
        ).get(HomeViewModel::class.java)

    }
}