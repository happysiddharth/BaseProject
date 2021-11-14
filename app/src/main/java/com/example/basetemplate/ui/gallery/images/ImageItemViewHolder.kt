package com.example.basetemplate.ui.gallery.images

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.basetemplate.R
import com.example.basetemplate.di.component.ViewHolderComponent
import com.example.basetemplate.ui.base.BaseItemViewHolder

class ImageItemViewHolder( view: View) : BaseItemViewHolder<Uri, ImageItemViewModel>(view) {
    val imageView = view.findViewById<ImageView>(R.id.image)
    override fun setUpObservers() {
        viewModel.data.observe(this){
            Glide
                .with(imageView)
                .load(it)
                .into(imageView)
          //  imageView.setImageURI(it)

        }
    }

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setUpView(view: View) {
    }
}