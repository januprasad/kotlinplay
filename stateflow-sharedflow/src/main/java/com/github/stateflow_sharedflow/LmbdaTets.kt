package com.github.stateflow_sharedflow

fun main() {
    val sum = { x: Int, y: Int -> x + y }  // Lambda with two parameters
    val shortened: (Int) -> Int = {
        it * 2

    }

    val a = { i: Int -> i + 1 }
//    println(shortened.invoke(3))
//    println(sum.invoke(3, 3))
//    println(intFunction(4))
    println(tester(4))
//    println(a(4))
//    println(4.intPlus(4))
    val p = (1..10).fold(1) { acc, i ->
        acc * i
    }
    println(p)
}

fun test(sum: (x: Int, y: Int) -> Int): Int {
    return sum.invoke(4, 5)
}

class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = x + x
}

val intPlus: Int.(Int) -> Int = Int::plus
val intFunction: (Int) -> Int = IntTransformer()
val tester: (Int) -> Int = Tester()

class Tester : (Int) -> Int {
    override operator fun invoke(p1: Int): Int = p1 * 3
}