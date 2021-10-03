package com.example.basetemplate.ui.dashboard

import android.Manifest
import android.database.Cursor
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basetemplate.R
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.di.component.FragmentComponent
import com.example.basetemplate.ui.base.BaseFragment
import com.example.basetemplate.ui.dashboard.users.UserAdapter
import com.example.basetemplate.ui.home.HomeViewModel
import com.example.basetemplate.util.log.Logger
import javax.inject.Inject
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.basetemplate.data.model.SMS
import kotlinx.android.synthetic.main.dashboard.*
import kotlinx.coroutines.*


class Dashboard:BaseFragment<DashboardViewModel>() {
    private val users = ArrayList<SMS>()
    private lateinit var rv : RecyclerView
    private lateinit  var adpater: UserAdapter
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    companion object{
        const val TAG = "Dashboard"
    }
    private lateinit var homeViewModel:HomeViewModel
    override fun setUpView(view: View) {
        rv = view.findViewById(R.id.users)
        adpater = UserAdapter(users,this);
        rv.layoutManager = linearLayoutManager
        rv.adapter = adpater
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        Logger.e(TAG,searchView.toString())
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchSms(newText.toString())
                return false
            }

        })



        super.onCreateOptionsMenu(menu, inflater)
    }


     override fun setObservers(view: View) {
         super.setObservers(view)
         viewModel.colName.observe(this){
             users.clear()
             users.addAll(it)
             adpater.notifyDataSetChanged()
             rv.scrollToPosition(0)
         }
         viewModel.loading.observe(this){
             if (it){
                 progress_circular.visibility = View.VISIBLE

             }else{
                 progress_circular.visibility = View.GONE
             }
         }


     }


    override fun getResourceId(): Int = R.layout.dashboard
    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }
}