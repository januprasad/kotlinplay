package com.github.interview_prep

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserAccount {
    suspend fun getBalance(): Int {
        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            count = 2000
            delay(100)
        }
        val deferred = CoroutineScope(Dispatchers.IO).async {
            // mimic second api response
            delay(400)
            return@async 100
        }
        return count + deferred.await()
    }

    suspend fun getBalance1(): Int {
        var count = 0
        var deferred: Deferred<Int>
        var deferred1: Deferred<Int>
        coroutineScope {
//            launch(Dispatchers.IO) {
//                delay(100)
//                count = 2000
//            }
            deferred = async (Dispatchers.IO){
                delay(1000)
                return@async 100
            }
            deferred1 = async (Dispatchers.IO){
                delay(2000)
                return@async 200
            }
//            launch(Dispatchers.IO) {
//                delay(100)
//                count += 1000
//            }
        }
        return deferred.await() + deferred1.await()
    }
}


suspend fun sample() {
    println("hello")
    CoroutineScope(Dispatchers.IO).launch {
        delay(100)
        println("world")
    }
}

suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
        delay(2000L)
        println("World2!")
    }
    println("Hello")

}

fun main() = runBlocking{ // this: CoroutineScope
    val response = Mobula().getPrice()
    val crypto = Mobula().parse(response)
    println(crypto.name)
}