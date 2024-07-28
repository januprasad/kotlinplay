package com.github.solid

fun main() {
    val s = "Hello how are    you"
    println(
        s
            .split(" ")
            .filter { it.isNotBlank() }
            .reversed()
            .joinToString(" "),
    )
    println(
        s
            .split(" ")
            .filter { it.isNotBlank() }
            .reversed()
            .joinToString(" "),
    )
}
