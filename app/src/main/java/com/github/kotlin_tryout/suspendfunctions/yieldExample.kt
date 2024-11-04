package com.github.kotlin_tryout.suspendfunctions

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        repeat(5) { i ->
            println("Coroutine 1: $i")
            yield() // Yielding control
        }
    }
    
    launch {
        repeat(5) { i ->
            println("Coroutine 2: $i")
            yield() // Yielding control
        }
    }
}