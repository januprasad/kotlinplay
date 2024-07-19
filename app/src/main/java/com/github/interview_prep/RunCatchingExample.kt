package com.github.interview_prep

fun main() {
    repeat(10) {
        runCatching {
            generateNumber()
        }.also {
            when (it.isSuccess) {
                true -> println("Success")
                false -> println("Failure")
            }
        }
    }
}

fun generateNumber(): Int {
    val num = (1..10).random()
    if (num % 2 == 0) return num else throw ArithmeticException()
}
