package com.example.basetemplate.ui.home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.R
import com.example.basetemplate.repo.UsersRepository
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.dashboard.Dashboard
import com.example.basetemplate.util.ViewModelFactory

class HomeActivity : BaseActivity() {

    override fun provideLayoutId(): Int = R.layout.home_layout

    private lateinit var viewModel:HomeViewModel

    override fun setupView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_dash, Dashboard(),Dashboard.TAG).commit()

        viewModel = ViewModelProviders
            .of(this, ViewModelFactory(HomeViewModel::class){
                HomeViewModel(UsersRepository())
            })
            .get(HomeViewModel::class.java)
        findViewById<Button>(R.id.btn).setOnClickListener {
            viewModel.changeTitle("changed")
            viewModel.fetUsers()
        }
    }

    override fun setObservers() {
        viewModel.title.observe(this,{
            findViewById<TextView>(R.id.text).text = it
        })

    }
}