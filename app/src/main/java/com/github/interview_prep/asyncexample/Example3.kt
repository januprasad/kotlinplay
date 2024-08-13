package com.github.interview_prep.asyncexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job =
            GlobalScope.launch {
                println("Throwing exception from coroutine")
                throw IllegalArgumentException()
            }
        job.join()
        println("Joined failed job")
    }
}
