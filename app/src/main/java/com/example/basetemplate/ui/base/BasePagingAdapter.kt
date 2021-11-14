package com.example.basetemplate.ui.base

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T:Any,VH:BaseItemViewHolder<T,out BaseItemViewModel<T>>>(
    private val dataList:ArrayList<T>,
    private val parentLifeCycle: Lifecycle,
    diffCallback: DiffUtil.ItemCallback<T>,
): PagingDataAdapter<T, VH>(diffCallback) {

    private var recyclerView: RecyclerView?= null
    init {
        parentLifeCycle.addObserver(
            object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onParentDestroy(){
                    recyclerView?.run{
                        for (i in 0 until childCount){
                            getChildAt(i).let{
                                (getChildViewHolder(it) as BaseItemViewHolder<*,*>).run{
                                    onDestroy()
                                    viewModel.onManualCleared()
                                }
                            }
                        }
                    }
                }
                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun onParentStop(){
                    recyclerView?.run{
                        if(layoutManager is LinearLayoutManager){
                            val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            val last = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            if (first in 0..last){
                                for (i in first..last){
                                    findViewHolderForAdapterPosition(i)?.let{
                                        (it as BaseItemViewHolder<*,*>).onStop()
                                    }
                                }
                            }
                        }
                    }
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun onParentStart(){
                    recyclerView?.run{
                        for (i in 0 until childCount){
                            getChildAt(i).let{
                                (getChildViewHolder(it) as BaseItemViewHolder<*,*>).run{
                                    onStart()
                                }
                            }
                        }
                    }
                }
            }
        )
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(data = dataList[position])
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onStop()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

}