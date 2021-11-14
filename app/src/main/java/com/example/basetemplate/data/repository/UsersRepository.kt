package com.example.basetemplate.data.repository

import androidx.paging.PagingSource
import androidx.paging.rxjava2.RxPagingSource
import com.example.basetemplate.data.model.Users
import com.example.basetemplate.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class UsersRepository  constructor(
    private val networkService: NetworkService
)  {
    fun fetUser(): ArrayList<Users> {
        val list: ArrayList<Users> = ArrayList();
        for (i in 0..10) {
            list.add(Users(i.toString(), "sidd$i"))
        }
        return list
    }






}