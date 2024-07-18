package com.example.problem_solving_questions

fun main() {
    val array = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
//    println(array.filter { it != 0 }.joinToString(separator = ""))
    for (index in (1 until array.size)) {
        println(array[index])
    }
}
