package com.example.trelloclone.models.userModels


import com.google.gson.annotations.SerializedName

data class UserModels(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("users")
    val users: List<UserX>
)