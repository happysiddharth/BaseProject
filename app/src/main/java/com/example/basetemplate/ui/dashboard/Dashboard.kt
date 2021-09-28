package com.example.basetemplate.ui.dashboard

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseFragment

class Dashboard:BaseFragment() {
    private val users = ArrayList<String>()
    companion object{
        const val TAG = "Dashboard"
    }
    override fun setUpView(view: View) {
        for (i in 0..10){
            users.add("siddharth${i}")
        }
        val rv : RecyclerView = view.findViewById(R.id.users)
        val adpater = UserAdapter(users);
        rv.adapter = adpater;
        rv.layoutManager = LinearLayoutManager(context)
    }

    override fun getResourceId(): Int = R.layout.dashboard
}