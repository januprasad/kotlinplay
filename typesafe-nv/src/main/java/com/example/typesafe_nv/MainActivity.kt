package com.example.typesafe_nv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.typesafe_nv.ui.theme.KotlinTryoutTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar =
                        {
                            TopAppBar(title = {
                                Text(text = "SampleApp")
                            })
                        },
                ) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        MainNavigation()
                    }
                }
            }
        }
    }
}
