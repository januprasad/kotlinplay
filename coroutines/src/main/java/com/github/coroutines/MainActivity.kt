package com.github.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.coroutines.ui.theme.KotlinTryoutsTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoroutinesDebugDemo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CoroutinesDebugDemo(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            scope.launch {
                try {
                    supervisorScope {
                        val deferred = async {
                            delay(100)
                            throw RuntimeException("Exception inside async coroutine with stack trace recovery!")
                        }
                        // Awaiting rethrows the exception with recovered stack trace including async/await suspension frames
                        deferred.await()
                    }
                } catch (e: Exception) {
                    // Print stack trace to logcat to inspect recovered stack trace frames
                    Log.e("CoroutinesDebug", "Caught exception with recovered stack trace:", e)
                }
            }
        }) {
            Text("Test Coroutines Stack Trace Recovery")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoroutinesDebugDemoPreview() {
    KotlinTryoutsTheme {
        CoroutinesDebugDemo()
    }
}
