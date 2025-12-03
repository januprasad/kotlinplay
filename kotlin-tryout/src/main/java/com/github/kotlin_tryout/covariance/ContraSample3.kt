package com.github.kotlin_tryout.covariance

fun main() {
    val subList = SubList<Int>()
    val list: List<Int> = listOf(1, 2, 3)
    subList.store(list.first())
    println(subList.first) // prints 1
}

class Integer

class SubList<in T> {
    lateinit var first: String

    fun store(list: T)  {
        first = list.toString()
    }
}
