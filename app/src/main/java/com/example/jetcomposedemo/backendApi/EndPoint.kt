package com.example.jetcomposedemo.backendApi

import com.example.jetcomposedemo.model.ResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {
    @GET("api/action/datastore_search")
    fun fetchData(@Query("offset") offset: Int,@Query("limit") limit: Int,@Query("resource_id") resource_id: String): Single<ResponseData>
}