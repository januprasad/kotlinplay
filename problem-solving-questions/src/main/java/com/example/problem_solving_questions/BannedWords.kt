package com.example.problem_solving_questions

fun main() {
    val paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    val words = paragraph.lowercase().split("\\W+|\\s+".toRegex())

    println(words)
    val banned = setOf("hit", "ball")
    val out =
        words
            .filterNot { it in banned }
            .groupingBy { it }
            .eachCount()
            .maxBy { it.value }
    println(out)
}
