package com.example.basetemplate.data.repository

import android.Manifest
import android.app.Activity
import android.net.Uri
import android.provider.Telephony
import com.example.basetemplate.data.model.SMS
import com.example.basetemplate.util.common.Constant.SMS_CONTENT_PROVIDER
import com.example.basetemplate.util.common.Permissions
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SMSRepository @Inject constructor(
    private val permissions: Permissions,
    private val activity: Activity
) {

    fun readSMS(): Single<ArrayList<SMS>> {
        if (permissions.checkPermission(activity, Manifest.permission.READ_SMS)) {
            val temp = ArrayList<SMS>()
            val cursor = activity.contentResolver
                .query(
                    Telephony.Sms.CONTENT_URI,
                    arrayOf(
                        Telephony.Sms._ID,
                        Telephony.Sms.BODY,
                        Telephony.Sms.DATE,
                        Telephony.Sms.ADDRESS,
                        Telephony.Sms.THREAD_ID,
                        Telephony.Sms.CREATOR,
                        Telephony.Sms.READ
                    ),
                    null,
                    null,
                    null
                )
            if (cursor!!.moveToFirst()) {
                do {
                    temp.add(
                        SMS(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6).toIntOrNull(),
                        )
                    )
                } while (cursor.moveToNext())
            }
            return Single.create { emitter -> emitter.onSuccess(temp) }
        } else {
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_SMS))
        }
        return Single.create { emitter -> emitter.onError(Throwable("Something went wrong")) }
    }

    fun searchSMS(searchString: String): Observable<ArrayList<SMS>> {
        if (permissions.checkPermission(activity, Manifest.permission.READ_SMS)) {
            val temp = ArrayList<SMS>()
            val cursor = activity.contentResolver
                .query(
                    Uri.parse(SMS_CONTENT_PROVIDER),
                    arrayOf(
                        Telephony.Sms._ID,
                        Telephony.Sms.BODY,
                        Telephony.Sms.DATE,
                        Telephony.Sms.ADDRESS,
                        Telephony.Sms.THREAD_ID,
                        Telephony.Sms.CREATOR,
                        Telephony.Sms.READ


                    ),
                    Telephony.Sms.BODY + " LIKE '%${searchString}%'",
                    null,
                    null
                )
            if (cursor!!.moveToFirst()) {
                do {
                    temp.add(
                        SMS(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6).toIntOrNull(),
                        )
                    )
                } while (cursor.moveToNext())
            }
            return Observable.create {
                it.onNext(temp)
            }
        } else {
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_SMS))
        }
        return Observable.create {
            it.onError(Throwable())
        }

    }

    fun smsDetails(smsID: String): Single<SMS> {
        if (permissions.checkPermission(activity, Manifest.permission.READ_SMS)) {
            val temp = ArrayList<SMS>()
            val cursor = activity.contentResolver
                .query(
                    Uri.parse(SMS_CONTENT_PROVIDER),
                    arrayOf(
                        Telephony.Sms._ID,
                        Telephony.Sms.BODY,
                        Telephony.Sms.DATE,
                        Telephony.Sms.ADDRESS,
                        Telephony.Sms.THREAD_ID,
                        Telephony.Sms.CREATOR,
                        Telephony.Sms.READ
                    ),
                    null,
                    null,
                    null
                )
            if (cursor!!.moveToFirst()) {
                temp.add(
                    SMS(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6).toIntOrNull(),
                    )
                )
            }
            return Single.create { emitter -> emitter.onSuccess(temp[0]) }
        } else {
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_SMS))
        }
        return Single.create { emitter -> emitter.onError(Throwable("Something went wrong")) }
    }
}