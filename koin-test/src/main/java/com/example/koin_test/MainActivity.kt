package com.example.koin_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.koin_test.ui.theme.KotlinTryoutTheme
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
            val postsViewModel: PostsViewModel = getViewModel()
            KotlinTryoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Text(text = "SampleApp") })
                    },
                ) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                       /* SettingsScreen(
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
                        )*/
                       /* LaunchedEffect(key1 = true) {
                            postsViewModel.getPosts()
                        }*/
                        val appState = postsViewModel.uiState.collectAsStateWithLifecycle()
                        PostScreen(appState.value)
                    }
                }
            }
        }
    }
}
