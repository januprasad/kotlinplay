package com.github.kotlin_tryout.debounce

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    val intFlow = flow {
        emit(1)
        delay(90)
        emit(2)
        delay(90)
        emit(3)
        delay(1010)
        emit(4)
        delay(1010)
        emit(5)
    }.debounce {
        if (it == 1) {
            0L
        } else {
            1000L
        }
    }
    runBlocking {
        intFlow.collect {
            println("$it")
        }
    }
}