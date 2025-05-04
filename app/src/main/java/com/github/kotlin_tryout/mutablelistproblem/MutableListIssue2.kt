package com.github.kotlin_tryout.mutablelistproblem

import okhttp3.internal.immutableListOf

fun main() {
    val numbers = mutableListOf(1, 2, 3)
    println("OG $numbers") // [1, 2, 3]
    add1000(numbers) // [1, 2, 3, 1000]
    println(numbers)
    clearAll(numbers)
    println(numbers)

    val numbers2 = immutableListOf(1, 2, 3)
    println("OG $numbers2") // [1, 2, 3]
    numbers2.plus(1000)  // similar like fn add1000
    println(numbers2) // [1, 2, 3]
}

fun add1000(list: MutableList<Int>) {
    list.add(1000)
}

fun clearAll(list: MutableList<Int>) {
    list.clear()
}