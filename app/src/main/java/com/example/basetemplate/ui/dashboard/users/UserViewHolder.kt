package com.example.basetemplate.ui.dashboard.users

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.basetemplate.R
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.ui.base.BaseItemViewHolder
import com.example.basetemplate.util.common.TimeUtils.getTimeAgo
import com.example.basetemplate.util.log.Logger
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class UserViewHolder(val view:View):BaseItemViewHolder<SMS, UserItemViewModel>(view) {


    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setUpView(view: View) {
        view.findViewById<TextView>(R.id.userName).setOnClickListener {
            viewModel.changeDataInList()
        }
    }

    override fun setUpObservers() {
        viewModel.data.observe(this, {
            view.findViewById<TextView>(R.id.userName).text = it.body
            view.findViewById<TextView>(R.id.sender).text = it.sender?.get(0).toString()
            view.findViewById<TextView>(R.id.time).text = getTimeAgo( Date(it.date!!.toLong()))
        })
    }
}