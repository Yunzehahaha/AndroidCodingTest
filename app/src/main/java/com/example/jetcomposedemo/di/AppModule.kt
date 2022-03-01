package com.example.jetcomposedemo.di

import com.example.jetcomposedemo.HomeViewModel
import com.example.jetcomposedemo.Tracker
import com.example.jetcomposedemo.backendApi.ApiClient
import com.example.jetcomposedemo.repository.MyRepository
import com.example.jetcomposedemo.room.MyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MyDatabase.getDatabase(androidApplication()) }
    single { ApiClient.create(androidApplication(), "https://data.gov.sg/") }
    single { MyRepository(get(), get<MyDatabase>().myDao()) }
    single { Tracker() }

    viewModel { HomeViewModel(get(), get()) }
}

val appModule = listOf(viewModelModule)