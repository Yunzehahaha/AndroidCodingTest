package com.example.jetcomposedemo.model

import com.google.gson.annotations.SerializedName

class ResponseResult {
    @SerializedName("resource_id")
    val resourseId: String = ""
    @SerializedName("records")
    val records: List<Record> = listOf()
    @SerializedName("_links")
    val links: Link? = null
    @SerializedName("offset")
    val offset: Int = 0
    @SerializedName("limit")
    val limit: Int = 0
    @SerializedName("total")
    val total: Int = 0
}

