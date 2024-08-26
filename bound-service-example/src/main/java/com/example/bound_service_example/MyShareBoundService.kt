package com.example.bound_service_example

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyShareBoundService : Service() {
    val data = mutableListOf<String>()
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService() = this@MyShareBoundService
    }

    override fun onBind(intent: Intent): IBinder = binder

    fun performTask(data: Int) {
        // Perform the task
        this.data.add("Task $data")
    }
}
