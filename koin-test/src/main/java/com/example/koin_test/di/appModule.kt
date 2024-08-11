package com.example.koin_test.di

import android.content.Context
import com.example.koin_test.SettingsRepository
import com.example.koin_test.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
//
// val appModule =
//    module {
//        // Single instance of a service
//        single { MyService() }
//
//        // Factory for creating repositories
//        factory { MyRepository() }
//    }
//
// class MyService {
//    fun doSomething() {
//    }
//    // ... service implementation
// }
//
// class MyRepository {
//    fun getData(): String = "Sample"
//    // ... repository implementation
// }

fun koinAppModule(context: Context) =
    module {
        single { SettingsRepository(get()) }
        single { context.getSharedPreferences("shared-preferences-delegates-example", Context.MODE_PRIVATE) }
        viewModel { SettingsViewModel(get()) }
    }
