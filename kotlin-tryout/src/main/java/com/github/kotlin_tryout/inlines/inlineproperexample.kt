package com.github.kotlin_tryout.inlines

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
