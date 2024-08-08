package com.github.interview_prep.parellismExample

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val threadPool =
            newFixedThreadPoolContext(4, "MyPool") // Create a thread pool with 4 threads

        val jobs =
            List(4) { index ->
                async(threadPool) {
                    delay(1000L) // Simulate some work
                    println("Task $index finished")
                }
            }

        jobs.awaitAll() // Wait for all jobs to complete
    }
}
