package com.example.jetcomposedemo

import android.util.Log

class Tracker {
    fun onYearViewingChange(year: String) {
        if (year != currentViewing) {
            currentViewing = year
            Log.d(TAG, "User is viewing $year's data")
        }
    }

    fun onOrentationChange(current: String) {
        if (current != currentOrientation) {
            currentOrientation = current
            Log.d(TAG, "User device is rotated to $current")
        }
    }

    companion object {
        val TAG = "AppTracker"
        var currentViewing = ""
        var currentOrientation = ""
    }
}