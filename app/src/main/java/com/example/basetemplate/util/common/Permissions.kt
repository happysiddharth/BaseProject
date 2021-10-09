package com.example.basetemplate.util.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.basetemplate.util.log.Logger
import java.security.Permission

object Permissions  {
    fun requestPermissions(activity: Activity,permissions:List<String>){
        permissions.forEach{permission->
            if (!checkPermission(activity,permission)){
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_SMS)){
                }else{
                    ActivityCompat.requestPermissions(activity, arrayOf(permission), 100)
                }

            }
        }
    }

     fun checkPermission(activity: Activity,permission:String):Boolean{
        if (ActivityCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }
}