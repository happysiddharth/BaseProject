package com.example.basetemplate.ui.dashboard.users

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.basetemplate.R
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.base.BaseItemViewHolder
import com.example.basetemplate.ui.home.BottomSheet
import com.example.basetemplate.util.common.Resource
import com.example.basetemplate.util.common.Status
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
        view.findViewById<RelativeLayout>(R.id.smsCard).setOnClickListener{
            viewModel.bottomSheet.postValue(Resource.success(data = viewModel.data.value!!))
        }
    }

    override fun setUpObservers() {
        viewModel.data.observe(this) {
            view.findViewById<TextView>(R.id.userName).text = it.body
            view.findViewById<TextView>(R.id.sender).text = it.sender
            view.findViewById<TextView>(R.id.time).text = getTimeAgo(Date(it.date!!.toLong()))
            if (it.read==1){
                view.findViewById<ImageView>(R.id.unread).visibility = View.GONE
            }else{
                view.findViewById<ImageView>(R.id.unread).visibility = View.VISIBLE

            }
        }
        viewModel.bottomSheet.observe(this){sms->
            when (sms.status){
                Status.SUCCESS ->{
                    val fm = (view?.context as BaseActivity<*>).supportFragmentManager
                   BottomSheet().apply {
                       arguments = Bundle().apply {
                           putSerializable("sms",viewModel.data.value!!)
                           show(
                               fm,
                               BottomSheet.TAG
                           )
                       }
                   }
                }
            }
        }
    }

}