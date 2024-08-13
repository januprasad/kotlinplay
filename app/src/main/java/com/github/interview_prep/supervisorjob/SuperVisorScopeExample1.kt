package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
        val j = scope.launch {
                try {
                    // Some code that might fail
                    delay(2000L)
                    throw Exception("Child 1 failed")
                } catch (e: Exception) {
                    println("Caught exception in child 1: $e")
                }
            }
        j.join()
        launch {
            // Other code that will continue running even if the first child fails
            println("Child 2 is still running")
        }
    }
}
