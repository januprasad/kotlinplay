package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val handler =
            CoroutineExceptionHandler { _, it ->
                println("Caught this: $it")
            }
        val job = SupervisorJob()

        val launcher1 =
            launch(job + handler) {
                repeat(10) {
                    println("Coroutine $it")
                    delay(500)
                    if (it == 9) {
                        throw IllegalStateException("An error occurred")
                    }
                }
            }

        val launcher2 =
            launch(job) {
                launcher1.join()
                delay(2000)
                println("This will print as its part of the supervisor job")
            }

        launcher2.join()
        println("Main: My children have finished!")
    }
}
