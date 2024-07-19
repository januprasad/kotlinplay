package com.github.stateflow_sharedflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SharedFlowVM : ViewModel() {
    val testInt = MutableSharedFlow<Int>()
    val tag = "App"
    var count = 0
    var job: Job? = null

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            delay(100)
//            testInt.emit(count)
//        }
//    }

    fun intent(intent: Intents) {
        when (intent) {
            Intents.Start -> {
                if (job === null) {
                    Log.v(tag, "Starting")
                    job =
                        viewModelScope.launch(Dispatchers.IO) {
                            repeat(1000) {
                                Log.v(tag, "increment on")
                                delay(1000)
                                count += 100
                                testInt.emit(count)
                            }
                        }
                }
            }

            Intents.Stop -> {
                if (job != null) {
                    Log.v(tag, "job cancel")
                    job?.cancel()
                    job = null
                }
            }
        }
    }
}

sealed class Intents {
    data object Start : Intents()

    data object Stop : Intents()
}
