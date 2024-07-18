package com.example.problem_solving_questions

fun main() {
    val excludes = listOf(1, 11, 111)
    val numbers = listOf(1, 123, 2232, 11, 343, 111, 11111, 111)
    val numbersRange = 1..2000
    println(numbers.filterNot { it in excludes })
    println(numbers.filterNot { it in numbersRange })
}
