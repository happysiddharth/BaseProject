package com.example.basetemplate.data.repository

import com.example.basetemplate.data.model.Users

class UsersRepository {
    fun fetUser(): ArrayList<Users> {
        val list:ArrayList<Users> = ArrayList();
        for (i in 0..10){
            list.add(Users(i.toString(),"sidd$i"))
        }
        return list
    }
}