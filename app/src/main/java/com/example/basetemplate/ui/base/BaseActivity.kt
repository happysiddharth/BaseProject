package com.example.basetemplate.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.basetemplate.MyApplication
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ActivityComponent
import com.example.basetemplate.di.component.DaggerActivityComponent
import com.example.basetemplate.di.module.ActivityModule
import com.example.basetemplate.util.log.Logger
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setSupportActionBar(setUpToolBar())
        viewModel.onCreate()
        setupView(savedInstanceState)
        setObservers()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setUpToolBar(): Toolbar

    @CallSuper
    protected open fun setObservers(){
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showToast(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showToast(this) }
        })
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStackImmediate()
        }else
            super.onBackPressed()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()


    protected fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes resId: Int) {
        Toast.makeText(applicationContext, getString(resId), Toast.LENGTH_SHORT).show()
    }

    protected fun showSnackBar(message: String) {
    }
}