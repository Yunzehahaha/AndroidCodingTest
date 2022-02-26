package com.example.jetcomposedemo.repository

import android.content.Context
import android.util.Log
import com.example.jetcomposedemo.backendApi.ApiClient
import com.example.jetcomposedemo.model.ResponseData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Repository(val context: Context) {
    val TAG: String = "Repo"
    val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
    val baseUrl = "https://data.gov.sg/"

    fun getTodayNews(offset: Int, limit: Int) {
        ApiClient.create(this.context, baseUrl).fetchData(offset,limit, resourceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseData> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "On Subscribe")
                }


                override fun onError(e: Throwable) {
                    Log.d(TAG, "On Error $e")
                }

                override fun onSuccess(t: ResponseData) {
                    Log.d(TAG, "${t.result?.records}")
                }
            })
    }
}