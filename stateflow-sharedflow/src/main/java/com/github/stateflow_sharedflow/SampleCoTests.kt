package com.github.stateflow_sharedflow

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            val result =
                async {
                    delay(2000)
                    "hello"
                }
            println(result.await())
        }
    }
    runBlocking {
        val ex =
            CoroutineExceptionHandler { coroutineContext, throwable ->
                println("Ex occured please check")
            }
        val scope = CoroutineScope(Dispatchers.IO + ex)
        scope
            .launch {
                val asyncResult: Deferred<String> =
                    async {
                        // Simulate some asynchronous operation (e.g., network call)
                        delay(100) // Simulate waiting time
                        throw ArithmeticException("Ex")
//                        "runBlocking/Dispatchers.Default/launch/async"
                    } // Wait for the async coroutine to finish and get its result
                launch {
                    println("runBlocking/Dispatchers.Default/launch/async")
                }
                println(asyncResult.await()) // Print the result after it's available
                launch {
                    println("runBlocking/Dispatchers.Default/launch/async 2")
                }
            }.join()
    }
}
