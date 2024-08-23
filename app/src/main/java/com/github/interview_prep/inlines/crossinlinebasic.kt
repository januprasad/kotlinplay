package com.github.interview_prep.inlines

fun main() {
    corssinlineTest {
        println("Hello")
//        return compiler wont allow
    }
    println("World")
}

inline fun corssinlineTest(crossinline func: () -> Unit) {
    func()
}
