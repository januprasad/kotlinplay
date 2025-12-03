package com.github.kotlin_tryout.foreachtest

fun main() {
    val s = listOf("Michelle", "Sally", "John", "Kenny")

    for ((name, index) in s.withIndex()) {
        println("$name$index")
    }
}
