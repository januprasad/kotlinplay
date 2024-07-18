package com.github.interview_prep

fun main() {
    val count = listOf(1, 0, 0, 8, 0, 2, 7, 0).filterNot { it == 0 }.count()
    val arr = listOf(1, 0, 0, 8, 0, 2, 7, 0) - listOf(0) + List(count) { 0 }
    println(arr)

    val numbers = mutableListOf(1, 2, 3, 2, 4, 8, 0, 7)
    // 1,3,4,4,0
    val subEvnIndex =
        numbers.filterIndexed { index, i ->
            index % 2 == 0
        }
    println(
        subEvnIndex,
    )
    val subOddIndex =
        numbers.filterIndexed { index, i ->
            index % 2 == 0
        }
    println(
        subOddIndex,
    )
}
