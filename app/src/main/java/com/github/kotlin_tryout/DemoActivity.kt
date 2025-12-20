package com.github.kotlin_tryout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DemoApp(modifier: Modifier, viewModel: MyViewModel = viewModel()) {
    val counter = viewModel.counter.observeAsState()
    Column(
        modifier
    ) {
        Text(counter.value.toString())
        Button(
            onClick = { viewModel.handleAction(ViewModelAction.Save) }
        ) {
            Text("Save 1")
        }
        val safeOnAction =
            rememberActionChannel(viewModel::handleAction)
        Button(onClick = { safeOnAction(ViewModelAction.Save) }) {
            Text("Save 2")
        }
        val safeOnActionHandler =
            rememberActionHandler(viewModel::handleAction)

        Button(
            onClick = { safeOnActionHandler(ViewModelAction.Save) }
        ) {
            Text("Save 3")
        }
    }
}

@Composable
fun <T> rememberActionChannel(onAction: (T) -> Unit): (T) -> Unit {
    val channel = remember { Channel<T>(Channel.UNLIMITED) }
    LaunchedEffect(onAction) {
        channel.consumeAsFlow().collect {
            Log.v("Demo", "Execute action using Channel")
            onAction(it)
        }
    }
    DisposableEffect(onAction) {
        onDispose {
            Log.v("Demo", "Channel Closed")
            channel.close()
        }
    }
    return remember(channel) {
        { action -> channel.trySend(action) }
    }
}

@Composable
fun <T> rememberActionHandler(onAction: (T) -> Unit): (T) -> Unit {
    val currentOnAction by rememberUpdatedState(onAction)
    val scope = rememberCoroutineScope()

    return remember {
        { action ->
            scope.launch {
                currentOnAction(action)
            }
        }
    }
}

/*
@Composable
fun DemoApp(
    modifier: Modifier,
    viewModel: MyViewModel = viewModel()
) {
    val safeOnAction =
        rememberActionChannel(viewModel::handleAction)
    Button(onClick = { safe0nAction(MyAction.Save) }) {
        Text("Save")
    }
}*/
