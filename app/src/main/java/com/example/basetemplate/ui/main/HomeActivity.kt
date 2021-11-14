package com.example.basetemplate.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ActivityComponent
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.sms.Sms
import com.example.basetemplate.ui.gallery.Gallery
import kotlinx.android.synthetic.main.home_layout.*

class HomeActivity() : BaseActivity<HomeViewModel>() {

    val users = ArrayList<String>()
    private var activeFragment: Fragment? = null

    override fun provideLayoutId(): Int = R.layout.home_layout


    override fun setupView(savedInstanceState: Bundle?) {
      // supportFragmentManager.beginTransaction().add(R.id.fragment_dash, Gallery(),Dashboard.TAG).commit()
        bottom_navigation.run{
            itemIconTintList = null
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.dashboard -> {
                        viewModel.onSmsSelected()
                        true
                    }
                    R.id.gallery -> {
                        viewModel.onGallerySelected()
                        true
                    }
                    else -> false
                }
            }
        }
    }



    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setUpToolBar(): Toolbar = findViewById(R.id.my_toolbar)

    override fun setObservers() {
        super.setObservers()
        viewModel.gallery.observe(this, Observer {
            it.getIfNotHandled()?.run { showGallery() }
        })

        viewModel.sms.observe(this, Observer {
            it.getIfNotHandled()?.run { showSMS() }
        })
    }

    private fun showSMS() {
        if (activeFragment is Sms) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Sms.TAG) as Sms?

        if (fragment == null) {
            fragment = Sms()
            fragmentTransaction.add(R.id.fragment_dash, fragment, Sms.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }
    private fun showGallery() {
        if (activeFragment is Gallery) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Gallery.TAG) as Gallery?

        if (fragment == null) {
            fragment = Gallery()
            fragmentTransaction.add(R.id.fragment_dash, fragment, Gallery.TAG)

        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

}