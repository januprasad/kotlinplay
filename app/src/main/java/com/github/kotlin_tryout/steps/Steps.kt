package com.github.kotlin_tryout.steps

fun main() {
    val oddNumbers = 1..100 step 2
    val evenNumbers = 2..100 step 2
    evenNumbers.forEach { println(it) }
    oddNumbers.forEach { println(it) }
}