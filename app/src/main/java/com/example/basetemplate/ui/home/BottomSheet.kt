package com.example.basetemplate.ui.home

import android.view.View
import android.widget.TextView
import com.example.basetemplate.R
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.ui.base.BaseBottomSheet

class BottomSheet : BaseBottomSheet(){
    companion object{
        const val TAG = "BottomSheet"
    }

    override fun getLayoutId(): Int = R.layout.bottom_sheet
    override fun setView(view: View) {
        arguments?.get("sms").let { sms->
            val sms:SMS = sms as SMS
            view.findViewById<TextView>(R.id.textView).setText(sms.body)
        }
    }


}