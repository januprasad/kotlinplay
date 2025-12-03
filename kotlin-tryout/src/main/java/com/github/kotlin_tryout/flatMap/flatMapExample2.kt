package com.github.kotlin_tryout.flatMap

fun main() {
    val sentences =
        listOf(
            "This is a sentence.",
            "This is another sentence.",
        )
    val words =
        sentences.flatMap {
            it.split(" ")
        }
    val uppercase =
        sentences.map {
            it.uppercase()
        }
    println(uppercase)
    println(words)
    val map = mapOf(1 to "A", 2 to "B", 3 to "C", 4 to "D")

    val nestedLists = listOf(listOf(1, 2), listOf(3, 4), listOf(4, 5, 6, 6, 6, 6, 6, 6, 6, 6))
    val flattenedList = nestedLists.flatMap { it }
    println(flattenedList)
    val sentences1 =
        listOf(
            listOf("This is some another sentence."),
            listOf(
                "This is a sentence.",
                "This is another sentence.",
            ),
        )
    val words1 =
        sentences1.flatMap { it }
    println(words1)
}
