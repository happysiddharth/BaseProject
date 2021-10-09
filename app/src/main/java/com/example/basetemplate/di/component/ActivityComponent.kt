package com.example.basetemplate.di.component

import androidx.core.app.ActivityCompat
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.di.ActivityScope
import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.di.module.ApplicationModule
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.home.HomeActivity
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.common.Permissions
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

@ActivityScope
@Component(
    modules = [
        ActivityModule::class
    ],
    dependencies = [
        ApplicationComponent::class
    ]
)
interface ActivityComponent {
    fun inject(homeActivity:HomeActivity)
    fun getHomeComponent(): HomeViewModel
    fun getNetworkHelper(): NetworkHelper
    fun getActivity(): BaseActivity<*>
}