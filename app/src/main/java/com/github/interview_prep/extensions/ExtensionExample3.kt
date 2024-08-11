package com.github.interview_prep.extensions

import kotlin.math.pow

fun main() {
    val number = 78345

    operator fun Int.get(digit: Int): Int =
        div(
            10.0
                .pow(digit.toDouble()),
        ).rem(10.0)
            .toInt()
    println(number[0])
    println(number[1])
    println(number[2])
    println(number[3])

//    listOf(2, 4, 5, 2, 1, 1, 1, 3, 2, 1, 1).forEach(::println)
}
