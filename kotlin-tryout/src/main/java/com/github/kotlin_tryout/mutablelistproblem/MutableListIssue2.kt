package com.github.kotlin_tryout.mutablelistproblem


fun main() {
    val numbers = mutableListOf(1, 2, 3)
    println("OG $numbers") // [1, 2, 3]
    add1000(numbers) // [1, 2, 3, 1000]
    println(numbers)
    clearAll(numbers)
    println(numbers)

}

fun add1000(list: MutableList<Int>) {
    list.add(1000)
}

fun clearAll(list: MutableList<Int>) {
    list.clear()
}