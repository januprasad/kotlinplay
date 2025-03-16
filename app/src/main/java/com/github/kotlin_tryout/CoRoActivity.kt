package com.github.kotlin_tryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CoRoActivity : ComponentActivity() {
    val version = BuildConfig.DATABASE_VERSION

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MyHelper.getInstance(this)
        val handler =
            CoroutineExceptionHandler { _, t ->
                println("Caught this $t")
            }
        CoroutineScope(Dispatchers.IO + handler).launch {
            supervisorScope {
                val def =
                    async {
                        delay(1000L)
                        val j =
                            launch {
                                delay(2000L)
                                println("Fourth co")
                            }
                        j.join()
                        throw IllegalArgumentException()
                    }
                launch {
                    def.join()
                }
                launch {
                    delay(3000L)
                    println("Second co")
                }
                launch {
                    delay(2000L)
                    println("Third co")
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = { Text(text = "Hello") })
                }) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        val counter =
                            remember {
                                mutableIntStateOf(0)
                            }
                        Text(text = "Hello, Android ${counter.value}")

                        Button(onClick = {
                            lifecycleScope.launch(Dispatchers.IO) {
//                                try {
                                delay(13000)
                                val result = version.plus(1)
                                println("Result: $result")
//                                } catch (e: Exception) {
//                                    println("Error: $e")
//                                }
                            }
                        }) {
                            Text(text = "Submit")
                        }
                        Button(onClick = {
                            counter.value = counter.value + 1
                        }) {
                            Text(text = "Increment")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Sadhanam destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        println("Sadhanam restart")
    }
}
