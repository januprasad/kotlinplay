package com.github.stateflow_sharedflow

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
   val job = launch {
       repeat(10){ i->
           println("co is waiting $i")
           delay(4000L)
       }
   }
    delay(3000L)
    println("Main thread is tired of waiting!")
    job.cancelAndJoin()
    println("Main thread : now i can quit")
}