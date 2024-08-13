package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        val j =
            scope.launch {
                launch {
                    // Other code that will continue running even if the first child fails
                    sortData()
                    println("child 2 coroutine is still running")
                }
                val result =
                    async {
                        val resul = sortData()
                        // Other code that will continue running even if the first child fails
                        println("Child 3 async is still running")
                        resul
                    }
                println(result.await())
                try {
                    // Some code that might fail
                    delay(1000L)
                    throw Exception("Child 1 failed")
                } catch (e: Exception) {
                    println("Caught exception in child 1: $e")
                }
            }
        j.join()
        launch {
            // Other code that will continue running even if the first child fails
            println("Another coroutine is still running")
        }
    }
}

suspend fun sortData(): String {
    delay(3000L)
    return "Hello"
}
