package com.github.interview_prep.delgates

fun main() {
    val c = CountingInt(3)
    c.count = 7
    println(c.count)
}

class CountingInt(
    initialValue: Int,
) {
    var count: Int = initialValue
        set(value) {
            field = value
            println("Incremented")
        }
}
