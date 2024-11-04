package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun main() =
    runBlocking {
        val job =
            launch {
                try {
                    repeat(1000) { i ->
                        println("I'm sleeping $i ...")
                        delay(1500L)
                    }
                } catch (e: CancellationException) {
                    println("Job was cancelled")
                } finally {
                    println("Job is finally complete")
                }
            }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
    }
