package com.github.interview_prep.extensions

fun main() {
    Subscription("Amazon Prime").test()
}

data class Subscription(
    val name: String,
)

fun Any.toString() {
    print("Object: $this")
}

fun Subscription.test()  {
    println("Subscription")
}
