package com.example.basetemplate.di.module

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basetemplate.data.repository.SMSRepository
import com.example.basetemplate.ui.base.BaseFragment
import com.example.basetemplate.ui.dashboard.DashboardViewModel
import com.example.basetemplate.util.ViewModelFactory
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment:BaseFragment<*>) {
    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providesDashboardViewModel(
        networkHelper: NetworkHelper,
        smsRepository: SMSRepository,
        compositeDisposable: CompositeDisposable
    ):DashboardViewModel =
        ViewModelProviders.of(fragment,
        ViewModelFactory(DashboardViewModel::class){
            DashboardViewModel(networkHelper,fragment.requireActivity(),smsRepository,compositeDisposable)
        }).get(DashboardViewModel::class.java)

    @Provides
    fun getActivity():Activity{
        return fragment.requireActivity()
    }
}