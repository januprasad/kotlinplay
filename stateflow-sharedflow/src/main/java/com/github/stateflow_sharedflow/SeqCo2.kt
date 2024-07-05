package com.github.stateflow_sharedflow

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            doTaskOne()
            doTaskTwo()
        }
    }
}