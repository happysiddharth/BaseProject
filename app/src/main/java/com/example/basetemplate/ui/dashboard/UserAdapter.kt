package com.example.basetemplate.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseAdapter

class UserAdapter(private val list:ArrayList<String>,parentLifeCycleOwner:LifecycleOwner) : BaseAdapter<String,UserViewHolder> (list,parentLifeCycleOwner.lifecycle){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list, parent, false)
        )
}