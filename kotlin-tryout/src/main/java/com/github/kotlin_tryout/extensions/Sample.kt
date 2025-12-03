package com.github.kotlin_tryout.extensions

fun main() {
    val dividend = 7
//    val divisor = 0
    repeat(dividend) { divisor ->
        println("$dividend - $divisor")
    }
}
