package com.github.januprasad.broadcast_play

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import android.os.Build

class MyApplication : Application() {
    private val securityReceiver = SecurityReceiver()
    private val otherAppReceiver = OtherAppReceiver()

    override fun onCreate() {
        super.onCreate()

        val securityIntentFilter =
            IntentFilter().apply {
                addAction(Intent.ACTION_BOOT_COMPLETED)
                priority = 100 // Higher priority
            }

        val otherAppIntentFilter =
            IntentFilter().apply {
                addAction(Intent.ACTION_BOOT_COMPLETED)
                priority = 50 // Lower priority
            }

        // Register the receivers programmatically
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API level 26 and above, we need to register receivers dynamically in some cases
            registerReceiver(securityReceiver, securityIntentFilter)
            registerReceiver(otherAppReceiver, otherAppIntentFilter)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(securityReceiver)
        unregisterReceiver(otherAppReceiver)
    }
}
