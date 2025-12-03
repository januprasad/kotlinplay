package com.github.kotlin_tryout.inlines

fun main() {
    calculateSum(
        2,
        4,
        {
            println(it)
            return@calculateSum
        },
        {
            println(it)
            return@calculateSum
        },
    )
    println("Done calculating")
}

inline fun calculateSum(
    i: Int,
    i1: Int,
    noinline addition: (Int) -> Unit,
    crossinline multiplication: (Int) -> Unit,
) {
    additionLogic(i, i1, addition)
    multiplicationLogic(i, i1) {
        multiplication(it)
    }
}

fun additionLogic(
    i: Int,
    i1: Int,
    addition: (Int) -> Unit,
) {
    addition(i + i1)
}

fun multiplicationLogic(
    i: Int,
    i1: Int,
    function1: (Int) -> Unit,
) {
    function1(i * i1)
}
