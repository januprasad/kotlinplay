package com.github.kotlin_tryout.equalscheck

import com.github.kotlin_tryout.scope.MyClass

fun main() {
    val a = MyClass("a", 22)
    val b = MyClass("a", 33)
    val c = a
    println(a == b) // prints: true
    println(a === b) // prints: true
    println(c === a) // prints: true
}
