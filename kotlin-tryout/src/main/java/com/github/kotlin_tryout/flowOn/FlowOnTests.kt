package com.github.kotlin_tryout.flowOn

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    val intFlow = flowOf(1, 2, 3, 4, 5)
    runBlocking {
       val x =  intFlow
            .flowOn(Dispatchers.IO)
            .map {
                it * 2
            }
            .flowOn(Dispatchers.Default)
            .filter { it % 2 == 0 }
            .singleOrNull()

        print("Hi $x")
    }
}