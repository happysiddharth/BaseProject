package com.example.basetemplate.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseAdapter

class UserAdapter(private val list:ArrayList<String>) : BaseAdapter<String,UserViewHolder> (list){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list, parent, false)
        )
}