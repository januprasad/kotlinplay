package com.github.stateflow_sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() {
    val mutex = Mutex()
    runBlocking {
        val job = launch {
            repeat(100) {
//                mutex.withLock {
                    counter++
                    println(counter)
//                }
                delay(1000L)
                println("Counter: $counter")
            }

        }
        job.join()
    }
}
