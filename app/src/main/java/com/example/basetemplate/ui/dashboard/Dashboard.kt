package com.example.basetemplate.ui.dashboard

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basetemplate.R
import com.example.basetemplate.di.component.FragmentComponent
import com.example.basetemplate.ui.base.BaseFragment
import com.example.basetemplate.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.dashboard.*

class Dashboard:BaseFragment<DashboardViewModel>() {
    private val users = ArrayList<String>()
    private lateinit var rv : RecyclerView
    private lateinit  var adpater:UserAdapter
    companion object{
        const val TAG = "Dashboard"
    }
    private lateinit var homeViewModel:HomeViewModel
    override fun setUpView(view: View) {

        rv = view.findViewById(R.id.users)
        adpater = UserAdapter(users,this);
        rv.adapter = adpater;
        rv.layoutManager = LinearLayoutManager(context)

        homeViewModel = ViewModelProvider(requireActivity())
            .get(HomeViewModel::class.java)
    }

     override fun setObservers(view: View) {
         homeViewModel.title.observe(this,{
             view.findViewById<TextView>(R.id.text2).text = it
         })

         homeViewModel.userList.observe(this,{
             users.addAll(it)
             adpater.differ.submitList(users)
         })

         viewModel.data.observe(this){
             text2.text = it
         }


    }


    override fun getResourceId(): Int = R.layout.dashboard
    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }
}