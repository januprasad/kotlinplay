package com.example.job_scheduler_example

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.job_scheduler_example.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var isBound by Delegates.notNull<Boolean>()
    private lateinit var myBoundService: MyService
    private lateinit var jobSchedulerHelper: JobSchedulerHelper
    private lateinit var connection: ServiceConnection
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        jobSchedulerHelper = JobSchedulerHelper(this)
        jobSchedulerHelper.scheduleJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        jobSchedulerHelper.cancelJob()
    }

    override fun onStart() {
        super.onStart()
        val i = Intent(this, MyService::class.java)
        startService(i)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    private val boundServiceConnection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName?,
                service: IBinder?,
            ) {
                isBound = true
                val binderBridge: MyService.MyBinder =
                    service as MyService.MyBinder
                myBoundService = binderBridge.getService()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = true
            }
        }

    override fun onResume() {
        super.onResume()
        val runnable =
            Runnable {
                Toast
                    .makeText(
                        this@MainActivity,
                        myBoundService.randomGenerator().toString(),
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        val handler = Handler()
        handler.postDelayed(runnable, 3000)
    }
}
