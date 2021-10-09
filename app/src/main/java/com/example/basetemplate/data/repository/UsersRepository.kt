package com.example.basetemplate.data.repository

import com.example.basetemplate.data.model.Users
import com.example.basetemplate.data.remote.NetworkService
import javax.inject.Inject

class UsersRepository @Inject constructor() {
    fun fetUser(): ArrayList<Users> {
        val list:ArrayList<Users> = ArrayList();
        for (i in 0..10){
            list.add(Users(i.toString(),"sidd$i"))
        }
        return list
    }

}