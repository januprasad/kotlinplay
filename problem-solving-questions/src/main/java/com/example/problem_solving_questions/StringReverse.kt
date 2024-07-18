package com.example.problem_solving_questions

fun main() {
    val ex1 = "hello    how are you"
//    println(
//        (
//            ex1
//                .split(" ")
//                .filter { it.isNotEmpty() }
//                .reversed()
//                .joinToString(" ")
//        ),
//    )
    val ex2 = "    hey what a surprise!!  "
    val ex3 = "im great thanks"
    listOf(ex1, ex2, ex3).map { it ->
        println(
            (it.split(" ") - listOf("")).reversed().joinToString(" "),
        )
    }
}
