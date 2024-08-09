package com.github.interview_prep.scope

fun main() {
    val number: Int? = null
    val result =
        number?.let {
            val number2 = number * 2
            number2
        } ?: 4
    println(result)
}
