package com.github.stateflow_sharedflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        runBlocking {
//            launch(Dispatchers.Main) {
//                println("Running on thread: ${Thread.currentThread().name}")
//            }
            launch(Dispatchers.Unconfined) { // will get its own new thread
                println("I'm working in thread ${Thread.currentThread().name}")
//                repeat(1){
                    delay(1000L)
//                }
                delay(300L)
                println("I'm tired of waiting")
                launch(Dispatchers.IO) {
                    println("I'm working in thread ${Thread.currentThread().name}")
                }
            }
        }
    }
}