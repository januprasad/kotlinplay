package com.github.interview_prep.equalscheck

import com.github.interview_prep.scope.MyClass

fun main() {
    val a = MyClass("a", 22)
    val b = MyClass("a", 33)
    println(a == b) // prints: true
    println(a === b) // prints: true
    println(a === a) // prints: true
}
