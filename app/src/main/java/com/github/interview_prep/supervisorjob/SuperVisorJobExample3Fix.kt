package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val handler =
            CoroutineExceptionHandler { _, t ->
                println("Caught this $t")
            }
        val scope =
            CoroutineScope(Dispatchers.Default + SupervisorJob())
        // wrong behavior no need to add this both.
        val job =
            scope.launch(handler) {
                val job =
                    launch {
                        delay(1000L)
                        println("Throwing exception from coroutine")
                        throw IllegalArgumentException()
                    }
                launch {
                    job.join()
                    delay(2000L)
                    println("This should not print")
                }
            }

        job.join()
        println("Joined failed job")
    }
}
