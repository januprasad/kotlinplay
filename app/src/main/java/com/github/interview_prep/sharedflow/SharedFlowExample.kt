package com.github.interview_prep.sharedflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            val sharedFlow =
                flow {
                    emit(1)
                    emit(2)
                    emit(3)
                }

// Emit some initial values

// Subscriber 1 subscribes
            sharedFlow.collect { value ->
                println("Subscriber 1 received: $value")
            }

// Subscriber 2 subscribes
            sharedFlow.collect { value ->
                println("Subscriber 2 received: $value")
            }
//
// // Emit another value
            sharedFlow.flowOn(Dispatchers.Default)
        }
    }
}
