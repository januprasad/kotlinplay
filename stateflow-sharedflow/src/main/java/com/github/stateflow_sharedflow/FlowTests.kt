package com.github.stateflow_sharedflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
//        flowTest1()
//        flowTest2()
//        flowTest3()
        flowTest4()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun flowTest4() {
    val flow1 = listOf(1, 2, 3).asFlow()
    val flow2 = listOf(4, 5, 6).asFlow()
    val flow3 = listOf(7, 8, 9).asFlow()
    flowOf(flow1, flow2, flow3)
        .flatMapConcat { it }
        .collect {
            println(it)
        }

}

suspend fun flowTest3() {
    val flow = listOf(1, 2, 3).asFlow()
    flow.filter {
        it % 2 == 0
    }.collect {
        println(it)
    }
}

suspend fun flowTest2() {
    val flowA = flowOf(
        1,
        2,
        3
    ).map { it + 1 } // Will be executed in ctxA     .flowOn(ctxA) // Changes the upstream context: flowOf and map
    flowA.collect {
        println(it)
    }
}

suspend fun flowTest1() {
    val flow = object : Flow<String> {
        override suspend fun collect(collector: FlowCollector<String>) {
            collector.emit("Hello")
            delay(1000)
            collector.emit("World")
        }
    }
    flow.collect {
        println(it)
    }
}
