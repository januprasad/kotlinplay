package com.github.interview_prep.extensions

fun main() {
    println(3 (2 + 2))
    println(3 + (2 + 2))
    println(3 + (2))
}

private operator fun Int.invoke(value: Int) = this + value
