package com.example.jetcomposedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetcomposedemo.model.Record
import com.example.jetcomposedemo.repository.MyRepository

class HomeViewModel(private val myRepository: MyRepository) : ViewModel() {
    val allData: LiveData<List<Record>> = myRepository.allData.asLiveData()

    fun loadData() {
        allData.value?.let {
            myRepository.loadData(it.size)
        } ?: run {
            myRepository.loadData(0)
        }
    }

    fun getDataByYear(items: List<Record>): List<Record> {
        var year: String = ""
        var volume: Double = 0.0
        val recordByYear: ArrayList<Record> = arrayListOf()
        items.forEach {
            if (year != it.quarter.substring(0, 4)) {
                year = it.quarter.substring(0, 4)
                volume += it.volumeOfMobileData
                recordByYear.add(Record(it.id, it.volumeOfMobileData, year))
                volume = 0.0
            } else {
                volume += it.volumeOfMobileData
            }
        }
        return recordByYear
    }
}