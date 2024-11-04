package com.github.stateflow_sharedflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.stateflow_sharedflow.ui.Theme.KotlinTryoutTheme

class SharedFlowActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Don't breath")
                        })
                    },
                ) { innerPadding ->
                    MySharedFlowApp(innerPadding)
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MySharedFlowApp(
    innerPadding: PaddingValues,
    viewModel: SharedFlowVM = viewModel(),
) {
    var state by rememberSaveable {
        mutableIntStateOf(0)
    }
    LaunchedEffect(true) {
        viewModel.testInt.collect {
            state = it
        }
    }

    Column(
        Modifier
            .padding(innerPadding)
            .padding(horizontal = 20.dp),
    ) {
        Text(text = state.toString(), fontSize = 80.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .padding(10.dp),
                onClick = { viewModel.intent(Intents.Start) },
            ) {
                Text(text = "Start")
            }
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .padding(10.dp),
                onClick = { viewModel.intent(Intents.Stop) },
            ) {
                Text(text = "Stop")
            }
        }
    }
}
