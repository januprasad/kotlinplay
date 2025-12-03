package com.github.kotlin_tryout.suspendfunctions

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

private val executor =
    Executors.newSingleThreadScheduledExecutor {
        Thread(it, "scheduler").apply { isDaemon = true }
    }

//fun main() {
//    runBlocking {
//        suspendCancellableCoroutine { continuation ->
//            println("Hello ${continuation.isActive}")
//            executor.schedule({
//                println("Coroutine is waiting...")
//                continuation.resume(Unit)
//            }, 1000, TimeUnit.MILLISECONDS)
//        }
//    }
//}
