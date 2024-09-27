package com.github.interview_prep

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.interview_prep.ui.theme.InterviewprepTheme

class StateTestActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewprepTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar =
                        {
                            TopAppBar(title = { Text(text = "Sample App") })
                        },
                ) { innerPadding ->
                    Column(
                        Modifier.padding(innerPadding),
                    ) {
                        Greeting3()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting3(
    viewModel: SampleStateViewModel =
        androidx.lifecycle.viewmodel.compose
            .viewModel(),
) {
    val state: String? by viewModel.name.collectAsStateWithLifecycle()

    val list =
        remember {
            mutableListOf(1, 2, 4, 5, 6, 7, 8)
        }

    val stateList =
        remember {
            mutableStateOf(list)
        }

    val sumStateList =
        remember {
            derivedStateOf {
                stateList.value.sum()
            }
        }
    val sumList =
        remember {
            derivedStateOf {
                list.sum()
            }
        }

    Text(text = sumStateList.value.toString(), fontSize = 40.sp)
    Text(text = sumList.value.toString(), fontSize = 40.sp)
    Button(onClick = {
        removeItems(stateList.value.toMutableList())
        removeItems(list)
        Log.v("items stateList", stateList.value.size.toString())
        Log.v("items list", list.size.toString())
    }) {
        Text(text = "Remove", fontSize = 25.sp)
    }
}

private fun removeItems(state: MutableList<Int>) {
    state.removeLast()
}
