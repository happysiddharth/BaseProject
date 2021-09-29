package com.example.basetemplate.ui.dashboard

import android.view.View
import android.widget.TextView
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.ui.base.BaseItemViewHolder

class UserViewHolder(val view:View):BaseItemViewHolder<String,UserItemViewModel<String>>(view) {
    override fun setData(data: String) {
        view.findViewById<TextView>(R.id.userName).text = data
    }

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setUpView(view: View) {
    }
}