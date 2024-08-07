package com.github.interview_prep.flatMap

fun main() {
    val listOfLists = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    val mixedList: List<Any> = listOf(1, 1, listOf(2, 3), 4, listOf(5, 6))
    val flattenedList = flatten(mixedList)
    val flattenedList1 = flatten(listOfLists)
    println(flattenedList)
    println(flattenedList1)
}

fun flatten(list: List<*>): List<Int> =
    list.flatMap {
        when (it) {
            is List<*> -> flatten(it)
            else -> listOf(it as Int)
        }
    }
