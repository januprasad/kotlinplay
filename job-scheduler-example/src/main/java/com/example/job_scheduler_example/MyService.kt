package com.example.job_scheduler_example

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.Random

class MyService : Service() {
    val binder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? = binder

    fun randomGenerator(): Int {
        val randomNumber = Random()

        val luckyNumber = randomNumber.nextInt()

        return luckyNumber
    }

    class MyBinder : Binder() {
        fun getService(): MyService = MyService()
    }
}
