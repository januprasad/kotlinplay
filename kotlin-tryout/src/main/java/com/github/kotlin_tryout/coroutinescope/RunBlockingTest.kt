package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        coroutineScope {
            suspendFunction2()
            suspendFunction1()
            println("Done!")
        }
    }
}

suspend fun suspendFunction2() {
    delay(1000L)
    println("suspendFunction2!")
}

suspend fun suspendFunction1() {
    delay(2000L)
    println("suspendFunction1!")
}
