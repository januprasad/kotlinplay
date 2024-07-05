package com.github.stateflow_sharedflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            launch {
                doTaskOne()
            }
        }
        withContext(Dispatchers.IO) {
            launch {
                doTaskTwo()
            }
        }
    }
}