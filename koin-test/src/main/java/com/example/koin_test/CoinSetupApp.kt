package com.example.koin_test

import android.app.Application
import com.example.koin_test.di.koinAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinSetupApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoinSetupApp)
//            modules(appModule)
            modules(koinAppModule(applicationContext))
        }
    }
}
