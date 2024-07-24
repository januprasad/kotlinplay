package com.github.stateflow_sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.IO)
        val job =
            scope.launch {
                println("Hello")
                delay(4000)
            }
    }
}
