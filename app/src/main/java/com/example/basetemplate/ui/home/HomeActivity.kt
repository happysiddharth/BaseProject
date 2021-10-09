package com.example.basetemplate.ui.home

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.basetemplate.R
import com.example.basetemplate.data.remote.Networking
import com.example.basetemplate.di.component.ActivityComponent
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.dashboard.Dashboard
import com.example.basetemplate.util.log.Logger
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeActivity() : BaseActivity<HomeViewModel>() {

    override fun provideLayoutId(): Int = R.layout.home_layout
    val users = ArrayList<String>()

    override fun setupView(savedInstanceState: Bundle?) {
       supportFragmentManager.beginTransaction().add(R.id.fragment_dash, Dashboard(),Dashboard.TAG).commit()
    }

    override fun setObservers() {
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpToolBar(): Toolbar = findViewById(R.id.my_toolbar)



}