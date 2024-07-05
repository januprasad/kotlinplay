package com.github.stateflow_sharedflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
    }
    println("sample")
}

fun flowSample(): Flow<Int> {
    return flow {
        emit(1)
    }
}

suspend fun flowSample1(): Flow<Int> {
//    val flow = (1..10).toList().map {
//        it * 2
//        delay(3000L)
//    }.asFlow()
    val flow1 = (1..10).toList().asFlow()
    return flow1

}

fun sample() {
    println("Hello ")
}