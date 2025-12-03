package com.github.kotlin_tryout.operatorfuns

fun main() {
    val sum = { x: Int, y: Int -> x + y } // Lambda with two parameters
    val shortened: (Int) -> Int = {
        it * 2
    }

    val a = { i: Int -> i + 1 }
    println(shortened(3))
    println(sum.invoke(3, 3))
    println(intFunction(4))
    println(tester(4))
    println(a(4))
    println(4.intPlus(4))
    val p =
        (1..10).fold(1) { acc, i ->
            acc * i
        }
    println(p)

    val sumeOfnubmber = (1..10).fold(0) { acc, i -> acc + i }
    println(sumeOfnubmber)

    val sumeOfnubmber1 = (1..10).reduce { acc, i -> acc * i }
    println(sumeOfnubmber1)
}

fun test(sum: (x: Int, y: Int) -> Int): Int = sum.invoke(4, 5)

class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = x + x
}

val intPlus: Int.(Int) -> Int = Int::plus
val intFunction: (Int) -> Int = IntTransformer()
val tester: (Int) -> Int = Tester()

class Tester : (Int) -> Int {
    override operator fun invoke(p1: Int): Int = p1 * 3
}
