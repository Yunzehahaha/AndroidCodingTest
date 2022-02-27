package com.example.jetcomposedemo.repository

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import com.example.jetcomposedemo.backendApi.ApiClient
import com.example.jetcomposedemo.model.Record
import com.example.jetcomposedemo.model.ResponseData
import com.example.jetcomposedemo.room.Dao
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MyRepository(
    private val context: Context,
    private val dao: Dao,
    private val scope: CoroutineScope
) {
    val allData: Flow<List<Record>> = dao.getAllData()
    private val TAG: String = "Repo"
    private val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
    private val baseUrl = "https://data.gov.sg/"
    private var total: Int? = null

    fun loadData(offset: Int) {
        Log.d(TAG, "On load")
        total?.let {
            if (offset >= it && offset != 0) {
                return
            }
        }
        ApiClient.create(this.context, baseUrl).fetchData(offset, 30, resourceId)
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
                    Log.d(TAG, "On Success data size: ${t.result?.records?.size}")
                    t.result?.let {
                        total = it.total
                    }
                    scope.launch {
                        t.result?.records?.let { records ->
                            records.forEach {
                                addNew(it)
                            }
                        }
                    }
                }
            })
    }

    @WorkerThread
    private suspend fun addNew(record: Record) {
        dao.addNewData(record);
    }
}