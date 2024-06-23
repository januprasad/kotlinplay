package com.github.interview_prep

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        repeat(5) {
            launch(Dispatchers.Default) {
                delay(1000)
                println("Ist API calling..... on ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                delay(2000)
                println("2nd API calling..... on ${Thread.currentThread().name}")
            }
        }
    }
}