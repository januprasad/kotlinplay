package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        repeat(5) {
            val job1 =
                launch(Dispatchers.Default) {
                    delay(1000)
                    println("Ist API calling..... on ${Thread.currentThread().name}")
                }
            val job2 =
                launch(Dispatchers.Default) {
                    delay(2000)
                    println("2nd API calling..... on ${Thread.currentThread().name}")
                }
            joinAll(jobs = arrayOf(job1, job2))
        }

        println("Done")
    }
}
