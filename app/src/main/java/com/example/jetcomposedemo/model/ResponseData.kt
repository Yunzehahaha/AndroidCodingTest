package com.example.jetcomposedemo.model

import com.google.gson.annotations.SerializedName

class ResponseData {
    @SerializedName("help")
    val help: String? = null
    @SerializedName("success")
    val isSuccess: Boolean = false
    @SerializedName("result")
    val result: ResponseResult? = null
}