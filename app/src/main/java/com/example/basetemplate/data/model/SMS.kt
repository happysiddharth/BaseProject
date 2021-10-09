package com.example.basetemplate.data.model

import java.io.Serializable

data class SMS(
    val _id:String,
    val body:String?,
    val date:String?,
    val sender:String?,
    val thread:String?,
    val creator:String?,
    val read:Int?,
):Serializable
