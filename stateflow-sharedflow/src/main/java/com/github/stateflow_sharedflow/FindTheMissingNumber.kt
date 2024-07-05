package com.github.stateflow_sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    val producerScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    val consumerScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    val counterChannel = Channel<Int>()
    val producer = producerScope.launch {
        for (i in 1..10) {
            counterChannel.send(i)
            delay(200)
        }
    }

    val consumer = consumerScope.launch {
//        counterChannel.receiveAsFlow().collect {
//            println(it)
//        } //working
        counterChannel.consumeEach { counter ->
            println(counter)
        }
    }
    runBlocking {
        producer.join()
        consumer.join()
    }
    runBlocking {
        producer.cancel()
        consumer.cancel()
    }
}

data class Student(val a: String)