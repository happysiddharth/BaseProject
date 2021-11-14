package com.example.basetemplate.ui.gallery

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basetemplate.R
import com.example.basetemplate.di.component.FragmentComponent
import com.example.basetemplate.ui.base.BaseFragment
import com.example.basetemplate.ui.gallery.images.ImagesAdapter
import kotlinx.android.synthetic.main.gallery_layout.*
import javax.inject.Inject

class Gallery : BaseFragment<GallaryViewModel>() {

    companion object{
        const val TAG = "Gallery"
    }

    private lateinit var imagesAdapter:ImagesAdapter
    private val uriList:ArrayList<Uri> = ArrayList()

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    override fun setUpView(view: View) {
        imagesAdapter = ImagesAdapter(uriList,this)
        view.findViewById<RecyclerView>(R.id.images).apply {
            adapter = imagesAdapter
            layoutManager = gridLayoutManager
        }
    }

    override fun getResourceId(): Int = R.layout.gallery_layout

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent
            .inject(this)
    }

    override fun setObservers(view: View) {
        super.setObservers(view)
        viewModel.uriList.observe(this){
            if (it.isNotEmpty()){
                Log.d("Sidd",it.size.toString())
                uriList.addAll(it)
                imagesAdapter.notifyDataSetChanged()
            }
        }
    }
}