package com.github.interview_prep.reified

fun main() {
    myrepeat(10) {
        println("Hello")
    }
    println("Hello2")

    repeat(10) {
        println("Hello3")
        return
    }
}

inline fun myrepeat(
    times: Int,
    crossinline action: (Int) -> Unit,
) {
    for (i in 0 until times) {
        action(i)
    }
}
