package com.github.mvi_sample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Handle side effects
    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { effect ->
            when (effect) {
                is HomeSideEffect.NavigateToDetail -> onNavigateToDetail(effect.itemId)
                is HomeSideEffect.ShowSnackbar -> snackbarHostState.showSnackbar(effect.message)
                is HomeSideEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }
    
    // Load data on start
    LaunchedEffect(Unit) {
        viewModel.onIntent(HomeIntent.LoadItems)
    }
    
    HomeContent(
        state = state,
        onIntent = viewModel::onIntent,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("MVI Architecture Sample") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        val message = when (val err = state.error) {
                            is HomeState.ErrorState.Network -> err.message
                            is HomeState.ErrorState.Unauthorized -> "Unauthorized"
                        }
                        Text(text = "Error: $message")
                        Button(onClick = { onIntent(HomeIntent.LoadItems) }, modifier = Modifier.padding(top = 8.dp)) {
                            Text("Retry")
                        }
                    }
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.items) { item ->
                            ListItem(
                                headlineContent = { Text(item.title) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onIntent(HomeIntent.ItemClicked(item.id)) }
                            )
                        }
                    }
                }
            }
        }
    }
}
