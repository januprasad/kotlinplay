package com.github.interview_prep.extensions

fun main() {
    println(3(4 + 2(3 + 4)))
}

private operator fun Int.invoke(value: Int) = this * value
