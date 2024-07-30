package com.github.januprasad.hilt_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                    val appState = quotesVM.uiState.collectAsStateWithLifecycle()
                    QuotesAppScreen(
                        appState = appState.value,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        quotesVM.events(it)
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun QuotesAppScreen(
    appState: AppState.UiState,
    modifier: Modifier = Modifier,
    onEvent: (Events) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onEvent(Events.RandomQuote)
    }
    when (appState.isLoading) {
        true -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red, strokeWidth = 3.dp)
            }
        }
        false ->
            Column(modifier) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(),
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = appState.data?.quote.orEmpty(),
                            fontSize = 20.sp,
                        )
                        Text(text = appState.data?.author.orEmpty(), fontSize = 16.sp)
                    }
                }
            }
    }
}
