package com.github.interview_prep

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.interview_prep.ui.theme.InterviewprepTheme
import kotlinx.coroutines.launch

class CoRo2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp2()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
}

@Composable
fun MainApp2() {
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        scope.launch {
            EventManager.updateEvent("MainApp visible")
        }
    }
    Column {
        Text(text = "Hello!", fontSize = 20.sp)
    }
}

object EventManager {
    fun updateEvent(event: String) {
        Log.v("Event", event)
    }
}
