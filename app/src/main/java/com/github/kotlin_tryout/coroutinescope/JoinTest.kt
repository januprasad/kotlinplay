package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) =
    runBlocking {
        val job =
            launch {
                // launch new coroutine and keep a reference to its Job
                delay(1000L)
                println("after one second World!")
            }
        println("Hello,")
        job.join() // wait until child coroutine completes
        println("Done,")
    }
