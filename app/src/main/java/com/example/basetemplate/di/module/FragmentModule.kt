package com.example.basetemplate.di.module

import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.repo.UsersRepository
import com.example.basetemplate.ui.base.BaseFragment
import com.example.basetemplate.ui.dashboard.DashboardViewModel
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.ViewModelFactory
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment:BaseFragment<*>) {

    @Provides
    fun providesDashboardViewModel(
        networkHelper: NetworkHelper
    ):DashboardViewModel =
        ViewModelProviders.of(fragment,
        ViewModelFactory(DashboardViewModel::class){
            DashboardViewModel(networkHelper)
        }).get(DashboardViewModel::class.java)
}