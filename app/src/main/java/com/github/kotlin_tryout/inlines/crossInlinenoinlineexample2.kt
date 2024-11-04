package com.github.kotlin_tryout.inlines

var count = 0

fun increment() {
    count++
}

inline fun higherOrderFunction(block: () -> Unit) {
    // Simulate passing block to another function
    println(count) // Output: 1
}

fun otherFunction(block: () -> Unit) {
    block() // Calling the passed lambda
}

fun main() {
    higherOrderFunction(::increment) // Passing a reference to increment function
    println(count) // Output: 1
}
