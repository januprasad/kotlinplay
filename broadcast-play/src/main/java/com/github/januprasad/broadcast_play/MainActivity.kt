package com.github.januprasad.broadcast_play

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
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
    ConnectivityListener,
    AirplanListener {
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

    private fun simpleAlertDialog() {
        // Create the AlertDialog builder
        val builder =
            AlertDialog
                .Builder(this)
                .setTitle("Alert")
                .setMessage("Airplane mode enabled!!")
                .setPositiveButton("OK") { dialog, which ->
                    // Handle positive button click
                    dialog.dismiss()
                }

        // Create the AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
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
        Log.v("App", "Sadhanam started")
    }

    override fun onPause() {
        super.onPause()
        Log.v("App", "Sadhanam paused")
        unregisterReceiver(airplaneModeDetector)
        unregisterReceiver(myLocalBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("App", "Sadhanam destroyed")
    }

    override fun onResume() {
        super.onResume()
        Log.v("App", "Sadhanam resumed")
        airplaneModeDetector = AirplaneModeDetector(this)
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeDetector, filter)

        /**
         * IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
         *  registerReceiver(airplaneModeDetector, filter)
         * }
         */
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("App", "Sadhanam restart")
    }

    override fun onStop() {
        super.onStop()
        Log.v("App", "Sadhanam stopped")
    }

    override fun airplaneMode() {
        simpleAlertDialog()
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
