package com.example.problem_solving_questions

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val groupingByEvenOdd = numbers.groupingBy { if (it % 2 == 0) "even" else "odd" }
    val counts = groupingByEvenOdd.eachCount()

    println(counts)
}
