package com.example.parcelize_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.parcelize_test.ui.Theme.KotlinTryoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val c = this
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App {
                        val i = Intent(c, HomeActivity::class.java)
                        startActivity(i)
                    }
                }
            }
        }
    }

    @Composable
    fun App(ok: () -> Unit) {
        Column {
            Button(onClick = { ok() }) {
                Text(text = "Launch")
            }
        }
    }
}
