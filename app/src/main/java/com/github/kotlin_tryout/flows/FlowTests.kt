package com.github.kotlin_tryout.flows

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val f1= flowOf(1,4,5,6,7,8,9,10)
    val f2= flowOf("A","B","C","D","E","F")
    f1.zip(f2) { intValue,stringValue ->
        "$intValue$stringValue"
    }.collect {
        println(it)
    }
}