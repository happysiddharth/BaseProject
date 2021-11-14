package com.example.basetemplate.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basetemplate.MyApplication
import com.example.basetemplate.di.component.DaggerFragmentComponent
import com.example.basetemplate.di.component.FragmentComponent
import com.example.basetemplate.di.module.FragmentModule
import com.example.basetemplate.util.log.Logger
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        setObservers(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getResourceId(), container, false)


    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((activity?.application as MyApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes resId: Int) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show()
    }

    protected fun showSnackBar(message: String) {
    }

    @CallSuper
    protected open fun setObservers(view: View){
        viewModel.messageString.observe(this, {
            it.data?.run { showToast(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showToast(this) }
        })
    }

    abstract fun setUpView(view: View)

    @LayoutRes
    abstract fun getResourceId(): Int
    abstract fun injectDependencies(fragmentComponent: FragmentComponent)

}