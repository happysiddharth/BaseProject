package com.example.basetemplate.ui.home

import android.os.Bundle
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.dashboard.Dashboard

class HomeActivity : BaseActivity() {

    override fun provideLayoutId(): Int = R.layout.home_layout

    override fun setupView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_dash,Dashboard(),Dashboard.TAG).commit()
        showToast("fragment attached")
    }
}