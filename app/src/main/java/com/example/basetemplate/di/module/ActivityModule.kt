package com.example.basetemplate.di.module

import android.app.Activity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.di.ViewHolderScope
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.ViewModelFactory
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity:BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @ActivityScope
    @Provides
    fun providesHomeViewModel(
        networkHelper: NetworkHelper,
        usersRepository: UsersRepository,
        compositeDisposable: CompositeDisposable
    ):HomeViewModel{
        return ViewModelProviders.of(activity,
            ViewModelFactory(
                HomeViewModel::class
            ) {
                HomeViewModel(networkHelper,usersRepository,compositeDisposable )
            }
        ).get(HomeViewModel::class.java)

    }


    @Provides
    fun getActivityInstance(): BaseActivity<*> = activity


}