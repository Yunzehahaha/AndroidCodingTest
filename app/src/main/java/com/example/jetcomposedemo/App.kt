package com.example.jetcomposedemo

import android.app.Application
import com.example.jetcomposedemo.repository.Repository

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val repo = Repository(applicationContext);
    }
}