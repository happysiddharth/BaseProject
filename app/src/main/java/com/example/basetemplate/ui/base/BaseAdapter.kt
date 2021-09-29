package com.example.basetemplate.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

//Generic class ->  we can pass data type as a variable
abstract class BaseAdapter<T:Any,VM:BaseItemViewModel<T>,VH:BaseItemViewHolder<T,VM>>(
    private val dataList:ArrayList<T>
) : RecyclerView.Adapter<VH>() {

    private val callBack = object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callBack)
    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(data = differ.currentList[position])
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        Logger.e("TAG",holder.toString())

    }
}