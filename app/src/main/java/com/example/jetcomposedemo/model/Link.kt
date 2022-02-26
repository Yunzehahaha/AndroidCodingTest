package com.example.jetcomposedemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Link {
    @SerializedName("start")
    @Expose
    val startLink: String? = null
    @SerializedName("next")
    @Expose
    val nextLink: String? = null
}