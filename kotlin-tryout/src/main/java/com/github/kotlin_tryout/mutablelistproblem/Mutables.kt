package com.github.kotlin_tryout.mutablelistproblem

fun main() {
    val squareList = MutableList(11) { i -> i * i } - 0
    val qubeList = MutableList(11) { i -> i * i * i } - 0
    val squareList1 = List(10) { (it + 1) * (it + 1) }
//    println(qubeList)
//    println(squareList)
    println(squareList1)

    println(
        squareList
            .zip(qubeList)
            .toMap(),
    )

    val squares = generateSequence(1) { it + 1 }.take(10).map { it * it }
    println(squares.toList())
    val squres1 = (1..10).map { it * it }
    println(squres1.toList())
}
