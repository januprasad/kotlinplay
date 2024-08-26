package com.example.bound_service_example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityBound1 : AppCompatActivity() {
    private lateinit var myBoundService: MyShareBoundService
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_bound1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Bind to the service
        val serviceIntent = Intent(this, MyShareBoundService::class.java)
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
        performTask()
    }

    private val connection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName?,
                service: IBinder?,
            ) {
                myBoundService = (service as MyShareBoundService.LocalBinder).getService()

                isBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }
        }

    // Call the service's method
    fun performTask() {
        if (isBound) {
            myBoundService.performTask(10)
        }
    }
}
