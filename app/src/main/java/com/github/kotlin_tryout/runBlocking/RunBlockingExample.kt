package com.github.kotlin_tryout.runBlocking

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        read()
        val j =
            launch {
                println("sample")
                delay(1000)
            }
//        j.join()
    }
    cancelOps()
}

suspend fun read() {
    println("Reading from")
}

fun cancelOps() {
    println("Suspending")
}
