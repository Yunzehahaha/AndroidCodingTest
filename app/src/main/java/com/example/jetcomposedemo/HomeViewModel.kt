package com.example.jetcomposedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetcomposedemo.model.Record
import com.example.jetcomposedemo.repository.MyRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val myRepository: MyRepository, private val tracker: Tracker) :
    ViewModel() {
    private val _allData: LiveData<List<Record>> = myRepository.allData.asLiveData()
    val allData: LiveData<List<Record>> get() = _allData

    private var selectedYear: String = ""
    private val allDataGroupByYear: ArrayList<List<Record>> = arrayListOf()

    val totalPage: Int get() = allDataGroupByYear.size
    val initPage: Int get() = getSelectedYearPageNum()

    fun loadData() {
        _allData.value?.let {
            myRepository.loadData(it.size)
        } ?: run {
            myRepository.loadData(0)
        }
    }

    fun getDataByPage(pageNum: Int): List<Record> {
        if (pageNum == allDataGroupByYear.lastIndex) {
            loadData()
        }
        return allDataGroupByYear[pageNum]
    }

    fun setSelectedYear(year: String) {
        selectedYear = year
        _allData.value?.let { groupDataByYear() }
    }

    private fun getSelectedYearPageNum(): Int {
        var result = 0
        allDataGroupByYear.forEach {
            if (it.first().quarter.substring(0, 4) == selectedYear) {
                return result
            } else {
                result++
            }
        }
        return result
    }

    fun groupDataByYear() {
        var currentYear = ""
        val sameYearRecord: ArrayList<Record> = arrayListOf()
        allDataGroupByYear.clear()
        _allData.value?.forEach { it ->
            if (currentYear != it.quarter.substring(0, 4)) {
                currentYear = it.quarter.substring(0, 4)
                if (sameYearRecord.isNotEmpty()) {
                    val tempList = sameYearRecord.map { element -> element.copy() }
                    allDataGroupByYear.add(tempList)
                }

                sameYearRecord.clear()
                sameYearRecord.add(it)
            } else {
                sameYearRecord.add(it)
            }
        }
        //add last yesr data
        if (sameYearRecord.isNotEmpty()) {
            val tempList = sameYearRecord.map { element -> element.copy() }
            allDataGroupByYear.add(tempList)
        }
    }

    fun getTotalDataByYear(): List<Record> {
        _allData.value?.let { groupDataByYear() }
        val recordByYear: ArrayList<Record> = arrayListOf()
        allDataGroupByYear.forEach {
            var volume: Double = 0.0
            it.forEach { element ->
                volume += element.volumeOfMobileData
            }
            recordByYear.add(
                Record(
                    id = it.first().id,
                    volumeOfMobileData = volume,
                    quarter = it.first().quarter.substring(0, 4),
                )
            )
        }
        return recordByYear
    }

    //tracking
    fun onYearPageChange(page: Int) {
        viewModelScope.launch {
            tracker.onYearViewingChange(allDataGroupByYear[page].first().quarter.substring(0, 4))
        }
    }

    fun onOrentationChange(current: String) {
        viewModelScope.launch {
            tracker.onOrentationChange(current)
        }
    }
}