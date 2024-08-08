package com.github.interview_prep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.github.interview_prep.ui.theme.InterviewprepTheme
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
                        delay(3000L)
                        launch {
                            println("Fourth co")
                        }
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

        lifecycleScope.launch(Dispatchers.IO) {
        }

        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = { Text(text = "Hello") })
                }) { innerPadding ->
                    Text(text = "Hello, Android!")
                }
            }
        }
    }
}
