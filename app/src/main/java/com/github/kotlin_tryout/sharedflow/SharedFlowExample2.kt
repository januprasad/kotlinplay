package com.github.kotlin_tryout.sharedflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun main() {
    val sharedFlow = MutableSharedFlow<String>()

    /*GlobalScope.launch {

        sharedFlow.collect { println(it) }            // Collection

        sharedFlow.emit("Ram")                       // Emission
        sharedFlow.emit("Kumar")
    }*/

    val sharedFlow1 = MutableSharedFlow<String>()

    GlobalScope.launch {
        sharedFlow1
            .onEach { println(it) }  // Collection
            .launchIn(this)
        launch {
            sharedFlow1.emit("Ram")
            sharedFlow1.emit("Kumar")      // Emission
        }
    }
}