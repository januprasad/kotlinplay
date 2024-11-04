package com.github.kotlin_tryout.infix

fun main() {
    println(5 add 5)
}

infix fun Int.add(other: Int): Int = this + other
