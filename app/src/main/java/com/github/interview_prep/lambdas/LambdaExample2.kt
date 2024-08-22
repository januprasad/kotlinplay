package com.github.interview_prep.lambdas

fun main() {
    println(calculator(4, 5, sum))
    println(calculator(4, 5, ::sum1))
}

fun calculator(
    a: Int,
    b: Int,
    ops: (Int, Int) -> Int,
): Int = ops(a, b)

val sum = { a: Int, b: Int -> a + b }

fun sum1(
    a: Int,
    b: Int,
) = a + b
