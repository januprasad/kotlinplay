package com.github.interview_prep.foreachtest

fun main() {
    val s = listOf("Michelle", "Sally", "John", "Kenny")

    for ((name, index) in s.withIndex()) {
        println("$name$index")
    }
}
