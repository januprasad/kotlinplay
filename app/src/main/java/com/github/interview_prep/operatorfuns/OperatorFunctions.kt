package com.github.interview_prep.operatorfuns

class Calculator {
    operator fun invoke(
        x: Int,
        y: Int,
        operation: String,
    ): Int =
        when (operation) {
            "+" -> x + y
            "-" -> x - y
            "*" -> x * y
            "/" -> x / y
            else -> throw IllegalArgumentException("Invalid operation")
        }
}

fun main() {
    val calculator = Calculator()
    val result = calculator(2, 3, "+")
    println(result) // Output: 5
    val result1 = calculator(2, 3, "/")
    println(result1) // Output: 5
    val result2 = calculator(2, 3, "*")
    println(result2) // Output: 5
}
