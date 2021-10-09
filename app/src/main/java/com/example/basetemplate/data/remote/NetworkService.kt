package com.example.basetemplate.data.remote

import retrofit2.http.GET

interface NetworkService {
    @GET(EndPoints.getUsers)
    suspend fun getPost():ArrayList<String>
}