package com.github.interview_prep.runBlocking

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Hello1")
    println("Hello2")
    waiter()
    println("Hello3")
    println("Hello4")
}

val waiter = {
    runBlocking {
        delay(2000L)
    }
}
