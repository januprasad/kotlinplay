package com.github.interview_prep.scope

class MyClass(
    val name: String,
) {
    init {
        println("Initialization block 1 $name")
    }

    var age = 30

    init {
        println("Initialization block 2 $name")
    }

    constructor(name: String, age: Int) : this(name) {
        println("Secondary constructor")
        this.age = age
    }
}

fun main() {
//    MyClass("JK")
    MyClass("JK", 20)
}
