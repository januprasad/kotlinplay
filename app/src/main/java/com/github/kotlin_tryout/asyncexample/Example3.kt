package com.github.kotlin_tryout.asyncexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job =
            GlobalScope.launch {
                delay(1000L)
                println("Throwing exception from coroutine")
                throw IllegalArgumentException()
            }
        job.join()
        println("Joined failed job")
    }
}
