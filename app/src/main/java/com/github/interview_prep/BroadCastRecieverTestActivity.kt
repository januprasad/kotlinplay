package com.github.interview_prep

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.interview_prep.broadcastreciever.AirplaneMOdeDetector

class BroadCastRecieverTestActivity : AppCompatActivity() {
    lateinit var airplaneModeDetector: AirplaneMOdeDetector
    lateinit var myLocalBroadcastReceiver: MyLocalBroadcastReceiver
    lateinit var localBroadcastManager: LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_broad_cast_reciever_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.trigger).setOnClickListener {
            localBroadcastManager = LocalBroadcastManager.getInstance(this)
            myLocalBroadcastReceiver = MyLocalBroadcastReceiver()
            localBroadcastManager.registerReceiver(
                myLocalBroadcastReceiver,
                IntentFilter("PlayGame"),
            )
            val localIntent =
                Intent("PlayGame")
                    // adding data to the intent
                    .putExtra("data", "PUBG")
            // sending our own broadcast
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)
        }
    }

    inner class MyLocalBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent,
        ) {
            when (intent.action) {
                "PlayGame" -> {
                    val result = intent.getStringExtra("data")
                    Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Action not found!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("Sadhanam started")
    }

    override fun onPause() {
        super.onPause()
        println("Sadhanam paused")
        unregisterReceiver(airplaneModeDetector)
        unregisterReceiver(myLocalBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Sadhanam destroyed")
    }

    override fun onResume() {
        super.onResume()
        println("Sadhanam resumed")
        airplaneModeDetector = AirplaneMOdeDetector()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airplaneModeDetector, it)
        }
    }

    override fun onRestart() {
        super.onRestart()
        println("Sadhanam restart")
    }

    override fun onStop() {
        super.onStop()
        println("Sadhanam stopped")
    }
}
