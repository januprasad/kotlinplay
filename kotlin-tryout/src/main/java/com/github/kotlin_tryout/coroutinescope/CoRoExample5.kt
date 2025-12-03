package com.github.kotlin_tryout.coroutinescope

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

fun main() {
    runBlocking {
//        delay2()
        delay(1000L)
    }
}

suspend fun delay2() {
    suspendCoroutine<Unit> { cont ->
        cont.resumeWith(Result.success(Unit))
    }
}
