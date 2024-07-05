package com.github.stateflow_sharedflow

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
            val resultOneDeferred = async { doTaskOne() }
            val resultTwoDeferred = async { doTaskTwo() }
            val combinedResult = resultOneDeferred.await() + resultTwoDeferred.await()
            println(combinedResult)
    }
}

suspend fun doTaskOne(): String {
    println("started doTaskOne")
    delay(2000)
    return "One"
}

suspend fun doTaskTwo(): String {
    println("started doTaskTwo")
    delay(2000)
    return "Two"
}