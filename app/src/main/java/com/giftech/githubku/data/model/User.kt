package com.giftech.githubku.data.model

data class User(
    val id:Int,
    val avatar:String,
    val username:String,
    val name:String = "",
    val bio:String = "",
)
