package com.github.handler_looper.demos

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.handler_looper.util.LogBus
import java.lang.ref.WeakReference

class LeakyActivity : ComponentActivity() {

    private var useSafeHandler by mutableStateOf(false)

    // Leaky Anonymous Handler holding implicit reference to outer Activity
    @Suppress("DEPRECATION")
    private val leakyHandler = Handler(Looper.getMainLooper())

    // Safe Static Handler with WeakReference
    private class SafeHandler(activity: LeakyActivity) : Handler(Looper.getMainLooper()) {
        private val activityRef = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            val activity = activityRef.get()
            if (activity != null && !activity.isFinishing) {
                LogBus.log("[SafeHandler] Received message safely without leaking activity.")
            } else {
                LogBus.log("[SafeHandler] Activity reclaimed by GC! Dropped message execution.")
            }
        }
    }

    private val safeHandler = SafeHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogBus.log("[LeakyActivity] onCreate()")

        setContent {
            MaterialTheme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Q17: Memory Leak Demo",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedCard(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = if (useSafeHandler) "Mode: SAFE (WeakReference + cleanup)" else "Mode: LEAKY (Implicit Handler reference)",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = if (useSafeHandler) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (useSafeHandler)
                                        "Using SafeHandler with WeakReference<Activity> and calling removeCallbacksAndMessages(null) in onDestroy()."
                                    else
                                        "Posting a 60-second delayed Runnable on an inner Handler. Rotating or finishing this Activity while message is pending leaks the entire Activity!"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (useSafeHandler) {
                                    LogBus.log("[LeakyActivity] Posting 60s delayed message via SafeHandler...")
                                    safeHandler.sendEmptyMessageDelayed(1, 60_000)
                                } else {
                                    LogBus.log("[LeakyActivity] Posting 60s delayed Runnable via LeakyHandler...")
                                    leakyHandler.postDelayed({
                                        LogBus.log("[LeakyHandler] Delayed runnable executed after 60s!")
                                    }, 60_000)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("1. Post 60s Delayed Task")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { useSafeHandler = !useSafeHandler },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (useSafeHandler) "Switch to LEAKY Mode" else "Switch to SAFE Mode")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { finish() },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("2. Finish Activity & Check Logs")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LogBus.log("[LeakyActivity] onDestroy()")
        if (useSafeHandler) {
            LogBus.log("[LeakyActivity] Cleaning up safeHandler messages in onDestroy()...")
            safeHandler.removeCallbacksAndMessages(null)
        } else {
            LogBus.log("[LeakyActivity] LEAK WARNING: leakyHandler still has pending 60s message in Main MessageQueue holding this Activity reference!")
        }
    }
}
