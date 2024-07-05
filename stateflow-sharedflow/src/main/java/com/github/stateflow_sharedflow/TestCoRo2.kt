package com.github.stateflow_sharedflow

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.System.currentTimeMillis

fun main() {
    runBlocking {
        println("Hello")
        val j = launch {
            // launched a coroutine and awaiting
            delay(3000L)
            println("launch")
        }
        j.join()
        runBlocking {
            delay(2000L)
            println("runBlocking")
        }
        j.cancelAndJoin()
        println("World")
    }
}

