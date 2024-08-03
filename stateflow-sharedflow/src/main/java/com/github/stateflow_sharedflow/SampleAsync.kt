package com.github.stateflow_sharedflow

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            val ageFirstDigit =
                async {
                    ageFirstDigit()
                }
            val ageSecondDigit =
                async {
                    ageSecondDigit()
                }
            val age = ageFirstDigit.await() + ageSecondDigit.await()
            println(age)
        }
    }
}
