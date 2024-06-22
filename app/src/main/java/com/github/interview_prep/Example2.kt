package com.github.interview_prep

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    /**
     * another
     */
//    val job = GlobalScope.launch {
//        println("Throwing exception from coroutine")
//        throw IllegalArgumentException()
//    }
//    job.join()
//    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException()
        42
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
    /**
     * another
     */
//    launch {
//        delay(1000L)
//        println("Task from runBlocking")
//    }
//    coroutineScope {
//        launch {
//            delay(2000L)
//            println("Task from nested launch")
//        }
//        delay(500L)
//        println("Task from coroutine scope")
//    }
//    println("Coroutine scope is over")
    /**
     * another
     */
//    val pJob = launch {
//        val c = launch {
//            while (true){
//                println("c running")
//                delay(1000L)
//            }
//        }
//        delay(2000L)
//        println("Canceling child")
//        c.cancel()
//    }
//    pJob.join()
    /**
     * another
     */
//    val c = Channel<Int>()
//    launch {
//        for (x in 1..5) c.send(x * x)
//        c.close()
//    }
//
//    repeat(5){
//        println(c.receive())
//        println("Done")
//    }
    /**
     * another
     */
//    launch {
//        for (k in 1..5) {
//            println("I'm not blocked $k")
//            delay(1000L)
//        }
//    }
//    launch {
//        for (k in 1..5) {
//            println("I'm not blocked $k*2")
//            delay(1000L)
//        }
//    }
//    num().collect {
//        println(it)
//    }
    /**
     * another
     */
//    coroutineScope {
//        val def1 = async {
//            println("reading news from paper")
//            delay(5000L)
//            println("still reading news")
//        }
//        val def2 = async {
//            delay(1000L)
//            println("drink coffee")
//        }
//        def1.await()
//        def2.await()
//        println("news & coffe Done")
//    }
    /**
     * another
     */
//    coroutineScope {
//        println("drinking coffee")
//        delay(2000L)
//    }
//    coroutineScope {
//        delay(2000L)
//        println("reading news")
//    }
//    println("Coroutine scope is over")
}

fun num() = flow {
    for (x in 1..5) {
        delay(1000L)
        emit(x * 2)
    }
}
