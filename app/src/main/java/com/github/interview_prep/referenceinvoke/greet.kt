package com.github.interview_prep.referenceinvoke

fun greet(name: String) {
    println("Hello, $name!")
}

val greeter = ::greet // Function reference

fun invokeGreeter(greeter: (String) -> Unit) {
    greeter("Alice")
}

fun main() {
    invokeGreeter(greeter) // Output: Hello, Alice!
    invokeGreeter(::greet) // Output: Hello, Alice!
}
