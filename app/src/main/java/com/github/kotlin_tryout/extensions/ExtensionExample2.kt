package com.github.kotlin_tryout.extensions

fun main() {
    println(11(11 + 11(22 + 22(33 + 33))))
    println(11(11 + 11(22 + 22(66))))
    println(11(11 + 11(22 + 1452)))
    println(11(11 + 11(1474)))
    println(11(11 + 16214))
    println(11(16225))
    println(178475)
    println(11(11 + 22(33 + 44)))
    println(11(11 + 22(33 + 55)))
    println(12 * 12 (12))
}

operator fun Int.invoke(value: Int) = this * value
