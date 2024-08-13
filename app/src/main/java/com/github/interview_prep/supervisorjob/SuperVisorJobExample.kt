package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val supervisorJob = SupervisorJob()
        val j1 =
            launch(supervisorJob) {
                try {
                    // Some code that might fail
                    delay(2000L)
                    throw Exception("Child 1 failed")
                } catch (e: Exception) {
                    println("Caught exception in child 1: $e")
                }
            }
        j1.join()

        val j2 =
            launch(supervisorJob) {
                // Other code that will continue running even if the first child fails
                println("Child 2 is still running")
            }
        j2.join()
    }
}
