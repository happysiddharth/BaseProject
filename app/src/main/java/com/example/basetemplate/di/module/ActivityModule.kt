package com.example.basetemplate.di.module

import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.repo.UsersRepository
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.ViewModelFactory
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity:BaseActivity<*>) {

    @Provides
    fun providesHomeViewModel():HomeViewModel{
        return ViewModelProviders.of(activity,
            ViewModelFactory(
                HomeViewModel::class
            ) {
                HomeViewModel(NetworkHelper(activity), UsersRepository())
            }
        ).get(HomeViewModel::class.java)

    }
}