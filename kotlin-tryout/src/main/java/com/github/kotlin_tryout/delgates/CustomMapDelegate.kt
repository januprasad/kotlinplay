package com.github.kotlin_tryout.delgates

class User(
    val map: Map<String, Any>,
) {
    val name: String by map
    val age: Int by map
}

fun main() {
    val user =
        User(
            mapOf(
                "name" to "John Doe",
                "age" to 25,
                "age1" to 28,
            ),
        )
    println(user.age)
}
