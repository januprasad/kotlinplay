package com.example.multithread_test

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val scheduler = Executors.newScheduledThreadPool(1)

    val task = Runnable { println("Running scheduled task") }

    scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS)
    scheduler.shutdown()
}
