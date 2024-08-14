package com.github.interview_prep.inlines

var count1 = 0

fun increment1() {
    count1++
}

inline fun higherOrderFunction1(noinline block: () -> Unit) {
    // Simulate passing block to another function
    otherFunction1(block)
}

fun otherFunction1(block: () -> Unit) {
    block() // Calling the passed lambda
}

fun main() {
    higherOrderFunction1(::increment1) // Passing a reference to increment function
    println(count1) // Output: 1
}
