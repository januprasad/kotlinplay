package com.example.problem_solving_questions

fun main() {
    val count = listOf(1, 0, 0, 8, 0, 2, 7, 0).filterNot { it == 0 }.count()
    val arr = listOf(1, 0, 0, 8, 0, 2, 7, 0) - listOf(0) + List(count) { 0 }
    println(arr)
}
