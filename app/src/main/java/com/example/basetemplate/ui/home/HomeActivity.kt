package com.example.basetemplate.ui.home

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ActivityComponent
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.dashboard.Dashboard
import com.example.basetemplate.util.log.Logger
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_layout.*
import java.util.concurrent.TimeUnit

class HomeActivity : BaseActivity<HomeViewModel>() {

    override fun provideLayoutId(): Int = R.layout.home_layout
    val users = ArrayList<String>()

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportFragmentManager.beginTransaction().add(R.id.fragment_dash, Dashboard(),Dashboard.TAG).commit()
    }

    override fun setObservers() {
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.search_menu_home, menu)
        return true
    }
}