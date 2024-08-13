package com.github.interview_prep.supervisorscope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() {
    runBlocking {
        supervisorScope {
            launch {
                // Other code that will continue running even if the first child fails
                println("This coroutine 1 is running")
                delay(1000L)
                throw Exception("Child 1 failed")
            }
            launch {
                delay(2000L)
                // Other code that will continue running even if the first child fails
                println("This coroutine 2 still running even the exception occured")
            }
            launch {
                delay(3000L)
                // Other code that will continue running even if the first child fails
                println("This coroutine 3 still running")
            }
        }
    }
}
