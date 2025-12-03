package com.github.kotlin_tryout.associatedBy

import com.github.kotlin_tryout.comparableexample.Person

fun main() {
    val map =
        listOf(
            "a" to 1,
            "b" to 2,
            "c" to 3,
            "a" to 22,
        )

    println(map.associateBy { it.first })

    val people =
        listOf(
            Person("Alice", 20),
            Person("Bob", 25),
            Person("Charlie", 25),
        )

    val peopleByAge = people.associateBy { it.age }
    println(peopleByAge.size) // Output: {25=[Alice, Charlie], 30=[Bob]}

    val words = listOf("apple", "banana", "banana", "apple", "orange", "orange", "banana")
    println("most repeated word " + words.groupingBy { it }.eachCount().maxByOrNull { it.value })

    println(
        "1 2 3 4 5 6 7 8 8 8 9 10 11 12 12 13 14 15"
            .split(" ")
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key,
    )
}
