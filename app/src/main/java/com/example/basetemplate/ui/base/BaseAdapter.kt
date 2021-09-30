package com.example.basetemplate.ui.base

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import com.example.basetemplate.util.log.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//Generic class ->  we can pass data type as a variable
abstract class BaseAdapter<T:Any,VM:BaseItemViewModel<T>,VH:BaseItemViewHolder<T,VM>>(
    private val dataList:ArrayList<T>,
    private val parentLifeCycle:Lifecycle
) : RecyclerView.Adapter<VH>() {

    private var recyclerView:RecyclerView ?= null
    init {
        parentLifeCycle.addObserver(
            object : LifecycleObserver{
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onParentDestroy(){
                    recyclerView?.runCatching{
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
                   Logger.e("TAG","onstoped")
                    recyclerView?.runCatching{
                        if(layoutManager is LinearLayoutManager){
                            val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            val last = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            if (first in 0..last){
                                for (i in first..last){
                                    findViewHolderForAdapterPosition(i)?.let{
                                        (it as BaseItemViewHolder<*,*>).onStart()
                                    }
                                }
                            }
                        }
                    }
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun onParentStart(){
                    recyclerView?.runCatching{
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

    private val callBack = object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.equals(newItem)
        }
    }
    val differ = AsyncListDiffer(this, callBack)
    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(data = differ.currentList[position])
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