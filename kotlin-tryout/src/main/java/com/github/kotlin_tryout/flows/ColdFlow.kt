package com.github.kotlin_tryout.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        testColdFlow()
//        testHotFlow()
        testMutableStateFlow()
    }
}
suspend fun testColdFlow() {
    val someColdFlow = flow<Int> {
        (1..5).forEach {
            delay(100)
            emit(it)
        }
    }

    someColdFlow.retry(2).collect {
        println(it)
    }
    delay(2000)
    someColdFlow.collect {
        println(it)
    }
}
suspend fun testHotFlow() {
    val someColdFlow = flow<Int> {
        (1..5).forEach {
            delay(100)
            emit(it)
        }
    }

//    someColdFlow.retry(2).collect {
//        println(it)
//    }
    delay(2000)
    someColdFlow.collect {
        println(it)
    }
}
fun sequenceFlow(): Flow<Int> = channelFlow {
    for (i in 1..5) {
        send(i)
    }
    close()
}
fun callbackFlow(): Flow<Int> = kotlinx.coroutines.flow.callbackFlow {
    send((1..5).random())
    awaitClose {  }
}
fun testMutableStateFlow(){
    runBlocking {
//        sequenceFlow().collect {
//            println(it)
//        }
        callbackFlow().retry(10).collect {
            println(it)
        }
    }
}
