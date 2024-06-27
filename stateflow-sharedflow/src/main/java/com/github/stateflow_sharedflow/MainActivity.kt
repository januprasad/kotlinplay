package com.github.stateflow_sharedflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.stateflow_sharedflow.ui.theme.InterviewprepTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(paddingValues = innerPadding)
                }
            }
        }
    }
}

@Composable
fun MyApp(paddingValues: PaddingValues, viewModel: MainViewModel = viewModel()) {
    var state by remember {
        mutableStateOf("")
    }
    var uiState = viewModel.testState.collectAsState()
    var name by remember {
        mutableStateOf("")
    }

    var text by remember { mutableStateOf(uiState.value.name) }
    var age by remember { mutableStateOf("") }
//    LaunchedEffect(key1 = true) {
//        viewModel.testChannel.receiveAsFlow().collect {
//            state = when (it) {
//                UIEvent.Green -> {
//                    it.toString()
//                }
//
//                UIEvent.Red -> {
//                    it.toString()
//                }
//            }
//        }
//    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .drawBehind {
            drawRect(
                color = resolveState(state)
            )
        }
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Name") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = age.toString(),
            onValueChange = {
                age = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Age") }
        )
        Button(onClick = { viewModel.updateState(name, age) }) {
            Text(text = "Submit")
        }
    }
}

fun resolveState(state: String): Color {
    return when (state) {
        UIEvent.Green.toString() -> Color.Green
        UIEvent.Red.toString() -> Color.Red
        else -> return Color.Cyan
    }
}
