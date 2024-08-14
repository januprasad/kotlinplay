package com.github.interview_prep.asyncexample

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking {
        val result =
            async {
                println("im working")
                delay(5000)
                return@async 100
            }
        val result1 =
            async {
                println("im also working")
                delay(1000)
                return@async 100
            }
        println(result.await() + result1.await())
    }

suspend fun compute(): Int {
    delay(1000L)
    return 20
}
