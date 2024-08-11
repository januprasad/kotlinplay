package com.example.koin_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.koin_test.ui.theme.InterviewprepTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
//    private val myService: MyService by inject()
//    private val myRepository: MyRepository by inject()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = getViewModel()
            InterviewprepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text(text = "SampleApp") })
                    },
                ) { innerPadding ->
//                    myService.doSomething()
//                    val data = myRepository.getData()
//                    WholeApp(innerPadding)
                    Column(Modifier.padding(innerPadding)) {
                        SettingsScreen(
                            viewModel.nickname.value,
                            {
                                viewModel.nickname.value = it
                            },
                            viewModel.turboMode.value,
                            {
                                viewModel.turboMode.value = it
                            },
                            viewModel.throttle.value,
                            {
                                viewModel.throttle.value = it
                            },
                            viewModel::save,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WholeApp(innerPadding: PaddingValues) {
    Text(
        text = "Hello!",
        color = Color.Red,
        style = MaterialTheme.typography.headlineLarge,
    )
}
