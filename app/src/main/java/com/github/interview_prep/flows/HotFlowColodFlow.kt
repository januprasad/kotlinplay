package com.github.interview_prep.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking
import java.io.IOException

fun main() =
    runBlocking {
        doLongRunningTask()
            .flowOn(Dispatchers.Default)
            .retry(retries = 4) { cause ->
                if (cause is IOException) {
                    delay(1000)
                    return@retry true
                } else {
                    return@retry false
                }
            }.catch {
                // error
            }.collect {
                // success
                println(it == 1000)
            }
//    testHoldFlow()
    }

private fun doLongRunningTask(): Flow<Int> =
    flow {
        // your code for doing a long running task
        // Added delay, random number, and exception to simulate
        delay(1000)
        val randomNumber = (0..2).random()
        if (randomNumber == 0) {
            throw IOException()
        } else if (randomNumber == 1) {
            throw IndexOutOfBoundsException()
        }
        delay(2000)
        emit(1000)
    }
