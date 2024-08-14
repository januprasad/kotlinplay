package com.github.interview_prep.inlines

fun main() {
    var count = 0
    noinlineExample(
        {
            count++
            println("Hello $count")
        }, // noinline lambda, can capture count
        {
            println("Hello $count")
        }, // crossinline lambda, cannot capture count
    )
}
