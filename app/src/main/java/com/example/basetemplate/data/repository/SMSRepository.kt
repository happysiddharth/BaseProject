package com.example.basetemplate.data.repository

import android.Manifest
import android.app.Activity
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.util.common.Permissions
import com.example.basetemplate.util.log.Logger
import io.reactivex.*
import org.reactivestreams.Subscriber
import javax.inject.Inject

class SMSRepository @Inject constructor(private val permissions:Permissions,
                                        private val activity: Activity) {

    fun readSMS():Single<ArrayList<SMS>>{
        if (permissions.checkPermission(activity, Manifest.permission.READ_SMS)){
            val temp = ArrayList<SMS>()
            val cursor = activity.contentResolver
                .query(
                    Uri.parse("content://sms"),
                    arrayOf(
                        Telephony.Sms._ID,
                        Telephony.Sms.BODY,
                        Telephony.Sms.DATE,
                        Telephony.Sms.ADDRESS
                    ),
                    null,
                    null,
                    null
                )
            if (cursor!!.moveToFirst()) {
                do {
                    temp.add(
                        SMS(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3))
                    )
                } while (cursor.moveToNext())
            }
            return Single.create { emitter -> emitter.onSuccess(temp) }
        }else{
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_SMS))
        }
        return Single.create { emitter -> emitter.onError(Throwable("Something went wrong")) }
    }

    fun searchSMS(searchString:String):Observable<ArrayList<SMS>>{
        if (permissions.checkPermission(activity, Manifest.permission.READ_SMS)){
            val temp = ArrayList<SMS>()
            val cursor = activity.contentResolver
                .query(
                    Uri.parse("content://sms"),
                    arrayOf(
                        Telephony.Sms._ID,
                        Telephony.Sms.BODY,
                        Telephony.Sms.DATE,
                        Telephony.Sms.ADDRESS
                    ),
                    Telephony.Sms.BODY+" LIKE '%${searchString}%'",
                    null,
                    null
                )
            if (cursor!!.moveToFirst()) {
                do {
                    temp.add(
                        SMS(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3))
                    )
                } while (cursor.moveToNext())
            }
            return Observable.create {
                it.onNext(temp)
            }
        }else{
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_SMS))
        }
        return Observable.create {
            it.onError(Throwable())
        }

    }
}