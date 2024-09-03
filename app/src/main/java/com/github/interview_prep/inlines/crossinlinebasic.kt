package com.github.interview_prep.inlines

fun main() {
    corssinlineTest {
        println("Hello")
//        return compiler wont allow
    }
    println("World")
}

// so basically it wont allow local return values
inline fun corssinlineTest(crossinline func: () -> Unit) {
    func()
}
