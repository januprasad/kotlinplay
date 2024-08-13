package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        /**
         * both jobs will fail, as not supervisor job
         */
        val handler =
            CoroutineExceptionHandler { _, t ->
                println("Caught this $t")
            }
        val scope = CoroutineScope(Dispatchers.Default + handler)
        val job =
            scope.launch(handler) {
                delay(1000L)
                println("Throwing exception from coroutine")
                throw IllegalArgumentException()
            }
        job.join()
        /**
         * as exception occured no further call
         */
        val job2 =
            scope.launch(handler) {
                delay(2000L)
                println("This should not print")
//            throw IllegalArgumentException()
            }
        job2.join()
    }
    println("Joined failed job")
}
