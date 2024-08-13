package com.github.interview_prep.asyncexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking {
//        val handler =
//            CoroutineExceptionHandler { _, exception ->
//                println("CoroutineExceptionHandler got $exception")
//            }
        launch {
            val job =
                GlobalScope.launch {
                    // root coroutine with launch
                    delay(1000L)
                    println("Throwing exception from launch")
                    throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
                }
            println("job")
            val deferred =
                GlobalScope.async {
                    delay(2000L) // root coroutine with async
                    println("Throwing exception from async")
                    throw ArithmeticException() // Nothing is printed, relying on user to call await
                }
            try {
                deferred.await()
                println("Unreached")
            } catch (e: ArithmeticException) {
                println("Caught ArithmeticException")
            }
            joinAll(job, deferred)
        }

        println("Job, deferred")
    }
