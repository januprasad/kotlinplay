package com.github.interview_prep.infix

fun main() {
    println(5 add 5)
}

infix fun Int.add(other: Int): Int = this + other
