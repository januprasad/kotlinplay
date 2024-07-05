package com.github.stateflow_sharedflow

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex()
var counter = 0

fun main() = runBlocking {
    val jobs = List(100) {
        launch {
            repeat(100) {
                mutex.withLock {
                    counter++
                }
            }
        }
    }
//    jobs.forEach { it.join() }
    delay(1000L)
    println("Counter: $counter")
}