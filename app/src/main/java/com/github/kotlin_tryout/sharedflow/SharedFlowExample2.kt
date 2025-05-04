package com.github.kotlin_tryout.sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val sharedFlow = MutableSharedFlow<String>(0)

    /*
     This wont work
     runBlocking {
            launch {
                sharedFlow.collect { println(it) }            // Collection
                launch {
                    sharedFlow.emit("Ram")                       // Emission
                    sharedFlow.emit("Kumar")
                }
            }
        }*/

    val sharedFlow1 = MutableSharedFlow<String>(2)

    /*
    Right way make reply 2 or  launch { sep for emit
    runBlocking {
        launch {
            sharedFlow1
                .onEach { println(it) }  // Collection
                .launchIn(this)
//            launch {
            sharedFlow1.emit("Ram")
            sharedFlow1.emit("Kumar")      // Emission
//            }
        }
    }*/

    runBlocking {
        launch {
            sharedFlow.onEach {
                println(it)
            }.launchIn(this)
            launch {
                sharedFlow.emit("ABC")
            }
        }
    }
}