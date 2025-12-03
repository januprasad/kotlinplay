package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking {
        val job =
            launch {
                delay(2000)
                println("in launch")
            }
        val job2 =
            launch {
                delay(2000)
                println("in launch 2")
            }
        joinAll(job2, job2, job2, job2)
        println("Hello 2")
        println("Hello!")
//        delay(5000)
//        println("World!")
//        delay(1000)
//        println("World2!")
    }
