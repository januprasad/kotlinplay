package com.github.kotlin_tryout.associatedBy

fun main() {
    val words = listOf("apple", "banana", "banana", "apple", "orange", "orange", "banana")
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