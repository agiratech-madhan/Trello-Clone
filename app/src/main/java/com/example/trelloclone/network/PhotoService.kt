package com.example.trelloclone.network

import com.example.trelloclone.models.userModels.UserModels
import retrofit.Call
import retrofit.http.GET

//https://dummyjson.com/users
interface PhotoService {
    @GET("users")
    fun getUsers(): Call<UserModels>
}