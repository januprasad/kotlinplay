package com.github.kotlin_tryout.flows

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    stateFlowTests()
//    sharedFlowTests()
    channelTests()
}

suspend fun channelTests() {
    val channel =Channel<Int>()
    channel.send(2)
    val r = channel.receive()
    println(r)
}

suspend fun sharedFlowTests() {
    val sharedFlow = MutableSharedFlow<Int>()
    sharedFlow.emit(3)
    sharedFlow.emit(43)
    sharedFlow.emit(143)
    sharedFlow.emit(1434)
    sharedFlow.collect {
        println(it)
    }
}

suspend fun stateFlowTests() {
    val stateFlow = MutableStateFlow(0)
    stateFlow.collect {
        println(it)
    }
}
