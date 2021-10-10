package com.example.basetemplate.ui.dashboard.sms

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.basetemplate.R
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.ui.base.BaseItemViewHolder
import com.example.basetemplate.ui.home.BottomSheet
import com.example.basetemplate.util.common.Event
import com.example.basetemplate.util.common.Resource
import com.example.basetemplate.util.common.Status
import com.example.basetemplate.util.common.TimeUtils.getTimeAgo
import java.util.*


class UserViewHolder(val view: View) : BaseItemViewHolder<SMS, UserItemViewModel>(view) {


    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setUpView(view: View) {
        view.findViewById<CardView>(R.id.smsCard).setOnClickListener {
            viewModel.bottomSheet.postValue(Event(Resource.success(viewModel.data.value!!)))
        }
    }

    override fun setUpObservers() {
        viewModel.data.observe(this) {
            view.findViewById<TextView>(R.id.userName).text = it.body
            view.findViewById<TextView>(R.id.sender).text = it.sender
            view.findViewById<TextView>(R.id.time).text = getTimeAgo(Date(it.date!!.toLong()))
            if (it.read == 1) {
                view.findViewById<ImageView>(R.id.unread).visibility = View.GONE
            } else {
                view.findViewById<ImageView>(R.id.unread).visibility = View.VISIBLE

            }
        }
        viewModel.bottomSheet.observe(this) { sms ->
            when (sms.getIfNotHandled()?.status) {
                Status.SUCCESS -> {
                    val fm = (view.context as BaseActivity<*>).supportFragmentManager
                    if (fm.findFragmentByTag(BottomSheet.TAG) == null)
                        BottomSheet().apply {
                            arguments = Bundle().apply {
                                putSerializable("sms", viewModel.data.value!!)
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