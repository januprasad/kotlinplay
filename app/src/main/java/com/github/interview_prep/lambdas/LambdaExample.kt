package com.github.interview_prep.lambdas

fun main() {
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println(sum.invoke(2, 3))
    println(UInt)
    val num: UInt = 232u
    println(num)
    println(Utils.logger)
}

object Utils {
    val logger = "logger"
}
