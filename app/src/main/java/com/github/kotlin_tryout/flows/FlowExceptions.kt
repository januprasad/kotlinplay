package com.github.kotlin_tryout.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job =
            launch {
                val numbers =
                    flow<Int> {
                        repeat(10) {
                            delay(1000L)
                            if (it == 9) {
                                throw Exception("An error occurred")
                            } else {
                                emit(it)
                            }
                        }
                    }.onCompletion {
                        println("Flow completed")
                    }

                try {
                    numbers
                        .collectLatest {
                            println(it)
                        }
                } catch (e: Exception) {
                    println("Error occurred")
                }
            }
        job.join()
    }
}
