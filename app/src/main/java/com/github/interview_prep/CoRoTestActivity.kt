package com.github.interview_prep

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.interview_prep.ui.theme.InterviewprepTheme

class CoRoTestActivity :
    ComponentActivity(),
    AnalyticalService by AnalyticalServiceImpl() {
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
                            Text(text = "Coroutine Testing")
                        })
                    },
                ) { innerPadding ->
                    AppScreen(innerPadding)
                }
            }
        }
        setup(this)
        event("On create")
    }
}

@Composable
fun AppScreen(
    innerPadding: PaddingValues,
    viewModel: CoRoViewModel = viewModel(),
) {
    viewModel.test()
    val result = viewModel.state.observeAsState()

    Column(Modifier.fillMaxSize().padding(innerPadding)) {
        Text(text = "Simple App ${result.value}")

        Button(onClick = { viewModel.updateState() }) {
            Text(text = "Click me")
        }
    }
}

interface AnalyticalService {
    fun event(event: String)

    fun setup(context: Context)
}

class AnalyticalServiceImpl : AnalyticalService {
    lateinit var context: Context

    override fun event(event: String) {
        Toast.makeText(context, "$event occurred", Toast.LENGTH_LONG).show()
    }

    override fun setup(context: Context) {
        this.context = context
    }
}

interface SampleService {
    fun test()
}

class SampleServiceImpl : SampleService {
    override fun test() {
        Log.v("App", "testing SampleService")
    }
}
