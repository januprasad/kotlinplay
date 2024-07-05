package com.github.stateflow_sharedflow

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    runBlocking {
//        println("Clothesline!")
//        launch {
//            println("Piledriver!")
//        }
//        println("Suplex!")
//        launch {
//            println("Sebatica!")
//        }
//        println("Figure-four Leglock!")
//        println("Pinning 1-2-3!")
//    }
    runBlocking {
        val job = launch {
            delay(3000L)
            println("Suplex!")
        }
//        job.join()
        println("Figure-four Leglock!")
        println("Clothesline!")
        runBlocking {
            println("Sebatica!")
        }
        // All three prints can potentially happen concurrently
    }

}