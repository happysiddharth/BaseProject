package com.example.basetemplate.ui.gallery.images

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.example.basetemplate.R
import com.example.basetemplate.ui.base.BaseAdapter

class ImagesAdapter(uriList:ArrayList<Uri>,parentLifecycleOwner: LifecycleOwner) : BaseAdapter<Uri, ImageItemViewHolder>(uriList,parentLifecycleOwner.lifecycle) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder =
        ImageItemViewHolder( LayoutInflater.from(parent.context)
            .inflate(R.layout.image_layout, parent, false))
}