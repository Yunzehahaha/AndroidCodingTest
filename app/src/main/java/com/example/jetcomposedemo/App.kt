package com.example.jetcomposedemo

import android.app.Application
import androidx.navigation.NavHostController
import com.example.jetcomposedemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule) }
    }
}