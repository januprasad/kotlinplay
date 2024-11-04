package com.github.kotlin_tryout.partition

data class User(
    val id: Int,
    val name: String,
    val isMentor: Boolean,
)

fun main() {
    val users =
        arrayOf(
            User(1, "Amit", true),
            User(2, "Ronaldo", false),
            User(1, "Messi", true),
            User(3, "Neymar", false),
        )

    val (mentors, notmentors) = users.partition { it.isMentor }
    println(notmentors)
}
