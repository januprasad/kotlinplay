package com.github.interview_prep.normalfunctions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            val result = readString()
            println(result)
            val result1 = readStringFile()
            println(result1)
        }
    }
}

/**
 * synchronously reads
 */
fun readString(): String {
    Thread.sleep(2000L)
    return "Hello World (Normal)"
}

/**
 * asynchronous reads
 */

suspend fun readStringFile(): String {
    delay(2000L)
    return "Hello World (Suspended)"
}
