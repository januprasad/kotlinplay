package com.github.interview_prep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.interview_prep.ui.theme.InterviewprepTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App(viewModel: MyViewModel = viewModel()) {
    val state = viewModel.uiState.collectAsState()
    when (state.value) {
        is AppState.Done -> {
            val data = (state.value as AppState.Done).crypto
            CryptoRow("Name", data.name)
            CryptoRow("Price", data.price.toString())
            CryptoRow("Code", data.symbol)
        }

        AppState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        AppState.Init -> {
            Button(onClick = {
                viewModel.fetchData()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Get Price!",
                )
            }
        }

        is AppState.Loaded -> TODO()
    }

}

@Composable
fun CryptoRow(param: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = param,
        )
        Text(
            text = value,
            color = Color.Gray
        )
    }
}
