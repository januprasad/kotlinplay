package com.github.stateflow_sharedflow

object Cons {
    const val NAME = "JK"
}

fun main() {
    println(Cons.NAME)

    val list = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10)
    println((1..10).toList().sum() - list.sum())
}
