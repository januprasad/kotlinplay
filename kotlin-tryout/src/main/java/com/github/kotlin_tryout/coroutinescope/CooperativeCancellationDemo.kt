package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("--- Part 1: The Stubborn Loop (Non-Cooperative) ---")
    val stubbornJob = launch(Dispatchers.Default) {
        var nextPrintTime = System.currentTimeMillis()
        var i = 0
        // This loop only cares about its own condition, not the coroutine state
        while (i < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Stubborn: I'm working on $i...")
                i++
                nextPrintTime += 500L
            }
        }
        println("Stubborn: I finished everything anyway!")
    }

    delay(700L)
    println("Main: Attempting to cancel stubbornJob...")
    stubbornJob.cancelAndJoin()
    println("Main: stubbornJob finally joined.\n")

    println("--- Part 2: The Cooperative Loop (Using isActive) ---")
    val cooperativeJob = launch(Dispatchers.Default) {
        var nextPrintTime = System.currentTimeMillis()
        var i = 0
        // isActive is the magic checkpoint!
        while (i < 5 && isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Cooperative: I'm working on $i...")
                i++
                nextPrintTime += 500L
            }
        }
        if (!isActive) {
            println("Cooperative: I see I was cancelled, stopping early!")
        }
    }

    delay(700L)
    println("Main: Attempting to cancel cooperativeJob...")
    cooperativeJob.cancelAndJoin()
    println("Main: cooperativeJob joined immediately.")
}
