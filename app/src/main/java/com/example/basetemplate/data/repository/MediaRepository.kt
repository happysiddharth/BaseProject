package com.example.basetemplate.data.repository

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.basetemplate.ui.base.BaseActivity
import com.example.basetemplate.util.common.Permissions
import io.reactivex.Single
import javax.inject.Inject

class MediaRepository  @Inject constructor(
    private val permissions: Permissions,
    private val activity: Activity
){

    fun getMedia():Single<ArrayList<Uri>>{
        if (permissions.checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            val list = ArrayList<Uri>()
            activity.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Images.Media.DATE_ADDED + " DESC",
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)

                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val size = cursor.getInt(sizeColumn)
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    list.add(Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,""+id))
                }
                return Single.create { emitter -> emitter.onSuccess(list) }

            }
        }else{
            permissions.requestPermissions(activity, listOf(Manifest.permission.READ_EXTERNAL_STORAGE))

        }
        return Single.create { emitter -> emitter.onError(Throwable("Something went wrong")) }

    }

}