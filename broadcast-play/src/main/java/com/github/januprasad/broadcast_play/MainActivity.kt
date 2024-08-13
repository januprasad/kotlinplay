package com.github.januprasad.broadcast_play

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.januprasad.broadcast_play.ui.theme.InterviewprepTheme

class MainActivity :
    ComponentActivity(),
    ConnectivityListener {
    lateinit var airplaneModeDetector: AirplaneModeDetector
    lateinit var myLocalBroadcastReceiver: MyLocalBroadcastReceiver
    lateinit var localBroadcastManager: LocalBroadcastManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Broadcast Notification Example")
                        })
                    },
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        sendBroadcast()
                    }
                }
            }
        }
    }

    private fun sendBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        myLocalBroadcastReceiver = MyLocalBroadcastReceiver()
        localBroadcastManager.registerReceiver(
            myLocalBroadcastReceiver,
            IntentFilter("PlayGame"),
        )
        // here we are creating our own action intent
        val localIntent =
            Intent("PlayGame")
                // adding data to the intent
                .putExtra("data", "PUBG")
        // sending our own broadcast
        localBroadcastManager.sendBroadcast(localIntent)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            // Internet connection is available, handle the scenario
        } else {
            // No internet connection, handle the scenario
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
        airplaneModeDetector = AirplaneModeDetector()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airplaneModeDetector, it)
        }

        myLocalBroadcastReceiver = MyLocalBroadcastReceiver()
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

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    sendBroadcast: () -> Unit,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
    Button(onClick = { /*TODO*/ }) {
        Text("Local Broadcast Notification")
    }
}
