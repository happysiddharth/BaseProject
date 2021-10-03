package com.example.basetemplate.ui.dashboard.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.basetemplate.R
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.ui.base.BaseAdapter

class UserAdapter(private val list:ArrayList<SMS>, parentLifeCycleOwner:LifecycleOwner) : BaseAdapter<SMS, UserViewHolder> (list,parentLifeCycleOwner.lifecycle){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return  UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list, parent, false)
        )
    }
}