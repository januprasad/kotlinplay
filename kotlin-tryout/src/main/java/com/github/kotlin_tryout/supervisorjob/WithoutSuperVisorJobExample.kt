package com.github.kotlin_tryout.supervisorjob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        /**
         * both threads will fail as not exception catching
         */

        val handler =
            CoroutineExceptionHandler { _, it ->
                println("Caught this " + it.message)
            }
        val scope = CoroutineScope(Dispatchers.Default + CoroutineName("MyScope") + SupervisorJob() + handler)
        val job =
            scope.launch {
                // Other code that will continue running even if the first child fails
                println("Child 1 is still running")
                delay(3000L)
                throw Exception("Child 1 failed")
            }

        val job2 =
            scope.launch {
                delay(2000L)
                job.join()
                // Other code that will continue running even if the first child fails
                println("Child 2 is still running")
            }
        job2.join()
        // Some code that might fail
    }
}
