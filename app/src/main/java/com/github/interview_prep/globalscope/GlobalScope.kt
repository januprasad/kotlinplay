package com.github.interview_prep.globalscope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        val job =
            GlobalScope.launch(Dispatchers.Default) {
                // Code that runs in a separate thread
                println("This is a coroutine in the GlobalScope")
                val result =
                    withContext(Dispatchers.IO) {
                        // Perform network or database operations here
                        getData()
                    }
                // default thread (conidering android it will main)
                updateUI(result)
            }
        job.join()
        // Other code that doesn't block the main thread
        println("This is the main thread")
    }
}

fun updateUI(result: String) {
    println("updating ui $result")
}

suspend fun getData(): String {
    delay(3000L)
    return "hello"
}
