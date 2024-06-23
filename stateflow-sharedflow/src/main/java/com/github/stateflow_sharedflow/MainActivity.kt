package com.github.stateflow_sharedflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.stateflow_sharedflow.ui.theme.InterviewprepTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: MainViewModel = viewModel()) {
    var state by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        viewModel.testEvent.collect {
            state = when(it) {
                UIEvent.Green -> {
                    it.toString()
                }

                UIEvent.Red -> {
                    it.toString()
                }
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .drawBehind {
            drawRect(
                color = resolveState(state)
            )
        })
}

fun resolveState(state: String): Color {
    return when (state) {
        UIEvent.Green.toString() -> Color.Green
        UIEvent.Red.toString() -> Color.Red
        else -> return Color.Cyan
    }
}
