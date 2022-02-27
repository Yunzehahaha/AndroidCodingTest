package com.example.jetcomposedemo.di

import com.example.jetcomposedemo.HomeViewModel
import com.example.jetcomposedemo.repository.MyRepository
import com.example.jetcomposedemo.room.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { CoroutineScope(SupervisorJob()) }
    single { MyDatabase.getDatabase(androidApplication(),get()) }
    single { MyRepository(androidApplication(),get<MyDatabase>().myDao(),get()) }
    viewModel { HomeViewModel(get()) }
}

val appModule = listOf(viewModelModule)