package com.example.trelloclone.network

import com.example.trelloclone.models.ShoppingModels.AllProducts
import retrofit.Call
import retrofit.http.GET

interface ProductServices {
    @GET("products")
    fun getAllProducts(): Call<AllProducts>
}