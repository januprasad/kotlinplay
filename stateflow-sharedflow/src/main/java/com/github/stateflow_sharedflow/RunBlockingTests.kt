package com.github.stateflow_sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun fetchData(): String {
    delay(2000)
    return "Data fetched from the network"
}

fun main() {
    // Define a CoroutineScope with a specific dispatcher
    val scope = CoroutineScope(Dispatchers.Default)

    // Launch a coroutine within the defined scope
    runBlocking {
        scope
            .launch {
                println("Fetching data...")

                // Call the suspend function
                val result = fetchData()

                println(result)
            }.join()
    }

    // Keep the main thread alive for a bit to see the coroutine result
    // optional step else join
//    Thread.sleep(3000)
}
