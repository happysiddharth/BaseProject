package com.example.basetemplate.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        setObservers()

    }

    protected abstract fun provideLayoutId():Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun setObservers()

    protected fun showToast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }
    protected fun showSnackbar(message: String){

    }
}