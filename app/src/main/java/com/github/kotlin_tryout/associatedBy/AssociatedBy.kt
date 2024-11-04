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
    println(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "11", "12", "13", "14", "15").maxOfOrNull { it.toInt() })
    println("Sample")
    println(
        "1 2 3 4 5 6 7 8 8 8 9 10 11 12 12 13 14 15"
            .split(" ")
            .maxOrNull(),
    )
    println(
        "15.3 2 3 4 5 6 7 8 8 8 9 10 11 12 12 13 14 15"
            .split(" ")
            .maxOfOrNull { it.toInt() },
    )
    println(
        (1..20).maxOrNull(),
    )
    val strings = arrayOf("1", "2", "3")
    val ints = strings.map { it.toInt() }.toTypedArray()

    val repeatedWords =
        words
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .keys

    println(repeatedWords) // Output: [apple]

    val wordFrequencies = words.groupingBy { it }.eachCount()

    println(wordFrequencies) // Output: {apple=2, banana=1, orange=1}
}
