package com.github.kotlin_tryout

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

class CoRoTestActivity :
    ComponentActivity(),
    AnalyticalService by AnalyticalServiceImpl() {
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
        lifecycleScope.launch {
            try {
                val x = 10 / 0
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
}

@Composable
fun MyComposable() {
    // Use myValue in the composable

    val (count, setCount) = remember { mutableIntStateOf(0) }
    val myValue =
        remember(key1 = count) {
            mutableIntStateOf(
                calculateExpensiveValue(),
            )
        }
    Text(text = "${myValue.intValue}")
    Button(onClick = { setCount(count + 1) }) {
        Text("Count: $count")
    }
}

fun calculateExpensiveValue() = Random(1).nextInt(1000)

@Composable
fun AppScreen(
    innerPadding: PaddingValues,
    viewModel: CoRoViewModel = viewModel(),
) {
    viewModel.test()
    val result = viewModel.state.observeAsState()

    val atomic = AtomicInteger(1)
    val counter =
        remember {
            mutableStateOf(atomic)
        }
    val mutex =
        remember {
            Mutex()
        }
    LaunchedEffect(true) {
        repeat(10) {
            launch {
                /*mutex.lock()
                try {
                counter.value = AtomicInteger(counter.value.get() + 1)
                delay(1000L)
                } finally {
                    mutex.unlock()
                }*/
                mutex.withLock {
                    counter.value = AtomicInteger(counter.value.get() + 1)
                    delay(1000L)
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Text(text = "Simple App ${result.value}")
        Text(text = "${counter.value}")

        Button(onClick = { viewModel.updateState() }) {
            Text(text = "Click me")
        }

        MyComposable()
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
