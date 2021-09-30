package com.example.basetemplate.ui.home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ActivityComponent
import com.example.basetemplate.repo.UsersRepository
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.dashboard.Dashboard
import com.example.basetemplate.util.ViewModelFactory
import com.example.basetemplate.util.log.Logger
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper

class HomeActivity : BaseActivity<HomeViewModel>() {

    override fun provideLayoutId(): Int = R.layout.home_layout

    override fun setupView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_dash, Dashboard(),Dashboard.TAG).commit()
        findViewById<Button>(R.id.btn).setOnClickListener {
            viewModel.changeTitle("changed")
            viewModel.fetUsers()
        }
    }

    override fun setObservers() {
        viewModel.title.observe(this,{
            showToast(it)
            Logger.e("TAG",it)
            findViewById<TextView>(R.id.text).text = it
        })

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}