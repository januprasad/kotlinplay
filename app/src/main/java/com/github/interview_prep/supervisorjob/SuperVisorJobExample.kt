package com.github.interview_prep.supervisorjob

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        /**
         * both jobs will execute as is it is under SupervisorJob
         */
        val supervisorJob = SupervisorJob()
        val j1 =
            launch(supervisorJob) {
                // Some code that might fail
                delay(1000L)
                throw Exception("Child 1 failed")
            }
        j1.join()

        val j2 =
            launch(supervisorJob) {
                // Other code that will continue running even if the first child fails
                // Some code that might fail
                delay(1000L)
                println("Hello baby")
//                throw Exception("Child 2 failed")
            }
        j2.join()
    }
}
