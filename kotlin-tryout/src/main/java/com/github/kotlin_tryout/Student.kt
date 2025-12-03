package com.github.kotlin_tryout

import kotlin.random.Random

data class Student(
    val name: String,
    val age: Int,
) {
    var id = 0
}

fun main() {
    val john = Student("John", 19)

    john.run {
        id = 1 // generate and save but readability issues there.
        println(id)
    }
    john.let {
        it.id = 2
        println(it.id)
        // un necessary take it
    }
    println(john.id)

    repeat(10) {
        val loginResult = listOf(true, false).random()
        println(loginResult)
    }
}

fun generateId() = Random(100).nextInt()
