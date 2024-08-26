package com.github.interview_prep.atomicinteger

import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val counter = AtomicInteger(0)

    // Increment the counter in multiple threads
    val threads =
        (1..10).map {
            Thread {
                for (i in 1..1000) {
                    counter.incrementAndGet()
                }
            }
        }

    threads.forEach { it.start() }
    threads.forEach { it.join() }

    println("Final counter value: ${counter.get()}")
}
