package com.example.basetemplate.di.component

import android.app.Activity
import androidx.core.app.ActivityCompat
import com.example.basetemplate.MyApplication
import com.example.basetemplate.data.remote.NetworkService
import com.example.basetemplate.data.repository.SMSRepository
import com.example.basetemplate.data.repository.UsersRepository
import com.example.basetemplate.di.ApplicationScope
import com.example.basetemplate.di.module.ApplicationModule
import com.example.basetemplate.util.common.Permissions
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
    fun getNetworkHelper(): NetworkHelper
    fun getPermissions(): Permissions
    fun getCompositeDisposable(): CompositeDisposable

}