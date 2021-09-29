package com.example.basetemplate.ui.base

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.basetemplate.MyApplication
import com.example.basetemplate.di.component.DaggerViewHolderComponent
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.di.module.ViewHolderModule
import javax.inject.Inject

abstract class BaseItemViewHolder<T:Any,VM:BaseItemViewModel<T>>(
    private val  view:View
): RecyclerView.ViewHolder(view),LifecycleOwner{

    init {
        onCreate()
    }
    @Inject
    lateinit var viewModel:VM

    @Inject
    lateinit var lifeCycleRegistry:LifecycleRegistry

    override fun getLifecycle(): Lifecycle = lifeCycleRegistry
    open fun setData(data:T){
        viewModel.updateData(data)
    }

    protected fun onCreate(){
        injectDependencies(buildViewHolderDaggerComponent())
        lifeCycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
        setUpObservers()
        setUpView(view)
    }

    fun onStarted(){
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.RESUMED)
    }
    fun onStop(){
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
    }
    fun onDestroy(){
        lifeCycleRegistry.markState(Lifecycle.State.DESTROYED)
    }


    protected fun setUpObservers(){

    }

    private fun buildViewHolderDaggerComponent()=
            DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as MyApplication).applicationComponent)
                .viewHolderModule(ViewHolderModule(this))
            .build()

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)
    protected abstract fun setUpView(view: View)

}