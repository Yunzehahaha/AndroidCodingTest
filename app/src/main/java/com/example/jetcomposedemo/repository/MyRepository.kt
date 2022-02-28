package com.example.jetcomposedemo.repository

import com.example.jetcomposedemo.backendApi.EndPoint
import com.example.jetcomposedemo.model.Record
import com.example.jetcomposedemo.model.ResponseData
import com.example.jetcomposedemo.room.Dao
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlin.concurrent.thread

class MyRepository(
    private val apiEndPoint: EndPoint,
    private val dao: Dao,
) {
    val allData: Flow<List<Record>> = dao.getAllData()
    private val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
    private var total: Int? = null
    var isSuccess: Boolean = false

    fun loadData(offset: Int) {
        total?.let {
            if (offset >= it && offset != 0) {
                return
            }
        }
        apiEndPoint.fetchData(offset, 30, resourceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseData> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    isSuccess = false
                }

                override fun onSuccess(t: ResponseData) {
                    t.result?.let {
                        total = it.total
                    }
                    isSuccess = true

                    t.result?.records?.let { records ->
                        addNew(records)
                    }
                }
            })
    }

    private fun addNew(record: List<Record>) {
        thread { dao.addNewData(record) }
    }
}