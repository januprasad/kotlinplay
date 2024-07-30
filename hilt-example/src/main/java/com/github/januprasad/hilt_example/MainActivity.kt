package com.github.januprasad.hilt_example

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.januprasad.hilt_example.ui.theme.InterviewprepTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Daily Quotes")
                        })
                    },
                ) { innerPadding ->
                    val quotesVM: MyQuotesVM = viewModel()
                    QuotesAppScreen(
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        quotesVM.events(it)
                    }
                }
            }
        }
    }
}

@Composable
fun QuotesAppScreen(
    modifier: Modifier = Modifier,
    onEvent: (Events) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onEvent(Events.RandomQuote)
    }
    Column(modifier) {
        Text(text = "Hello!")
    }
}
