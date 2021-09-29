package com.example.basetemplate.repo

class UsersRepository {
    fun fetUser(): ArrayList<String> {
        val list:ArrayList<String> = ArrayList();
        for (i in 0..10){
            list.add("ravi ${i}")
        }
        return list
    }


}