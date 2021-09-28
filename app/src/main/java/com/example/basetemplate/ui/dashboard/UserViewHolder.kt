package com.example.basetemplate.ui.dashboard

import android.view.View
import android.widget.TextView
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseItemViewHolder

class UserViewHolder(val view:View):BaseItemViewHolder<String>(view) {
    override fun setData(data: String) {
        view.findViewById<TextView>(R.id.userName).text = data
    }
}