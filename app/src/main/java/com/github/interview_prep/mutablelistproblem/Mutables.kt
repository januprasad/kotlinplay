package com.github.interview_prep.mutablelistproblem

fun main() {
    val squareList = MutableList(11) { i -> i * i } - 0
    val qubeList = MutableList(11) { i -> i * i * i } - 0
    println(qubeList)
    println(squareList)

    println(
        squareList
            .zip(qubeList)
            .toMap(),
    )

    val squares = generateSequence(1) { it + 1 }.take(10).map { it * it }
    println(squares.toList())
}
