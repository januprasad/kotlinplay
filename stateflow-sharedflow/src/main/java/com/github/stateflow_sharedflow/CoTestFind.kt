package com.github.stateflow_sharedflow

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val example: Int by lazy {
        0
    }
    println(example)
    runBlocking {
        val job = launch {
            delay(4000L)
            println("Done")
        }
        job.join()
//        delay(2000L)
        job.cancelAndJoin()
        println("Hello")
        println("World")
    }
}