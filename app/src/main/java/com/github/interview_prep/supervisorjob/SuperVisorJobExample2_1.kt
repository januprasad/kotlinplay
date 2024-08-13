package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        /**
         *
         */
        val job =
            launch {
                repeat(10) {
                    println("Coroutine $it")
                    delay(500)
                    if (it == 9) {
                        throw IllegalStateException("An error occurred")
                    }
                }
            }

        launch {
            job.join()
            delay(8000)
            println("This wont print as an exception thrown!")
        }

        println("Main: My children have finished!")
    }
}
