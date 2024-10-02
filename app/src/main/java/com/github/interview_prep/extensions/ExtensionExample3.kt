package com.github.interview_prep.extensions

import kotlin.math.pow
import kotlin.system.measureTimeMillis

fun main() {
    val number = 572806
//    println(number[-1]) // 5
    println(number[1]) // 5
    println(number[2]) // 7
    println(number[3]) // 2
    println(number[4]) // 8
    println(number[5]) // 0
    println(number[6]) // 6
    println(number[9]) // IndexOutOfBoundsException

    val tms =
        measureTimeMillis {
            val digits = (1..number.toString().length).map { number[it] }
            println(" digit 1st " + digits[0])
        }
    println("seconds " + tms)

    val tms1 =
        measureTimeMillis {
            println(number[6]) // 6
        }
    println("seconds " + tms1)
}

// fun Int.toArrayInteger() {}

operator fun Int.get(index: Int): Int {
    with("$this") {
        val actualIndex = length - index
        if (index <= 0 || index > length) {
            throw IndexOutOfBoundsException("Index $index out of bounds of length $length")
        }
        return div(10.0.pow(actualIndex.toDouble())).rem(10.0).toInt()
    }
}
