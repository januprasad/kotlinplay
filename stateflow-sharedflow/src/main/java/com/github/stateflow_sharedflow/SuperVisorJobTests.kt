package com.github.stateflow_sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking {
        val supervisor = SupervisorJob()

        val scope = CoroutineScope(Dispatchers.Default + supervisor)

        // Launching a child coroutine that will fail
        val failingChild =
            scope.launch {
                println("Failing child started")
                delay(1000)
                throw RuntimeException("Failure in child coroutine")
            }

        // Launching another child coroutine that should continue
        val anotherChild =
            scope.launch {
                try {
                    println("Another child started")
                    delay(2000)
                    println("Another child completed")
                } catch (e: Exception) {
                    println("Another child caught exception: ${e.message}")
                }
            }

        // Wait for both children to complete
        failingChild.join()
        anotherChild.join()

        println("Parent scope completed")
    }
