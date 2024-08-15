package com.github.interview_prep.delgates

/**
 * In this example, the Derived class delegates the print()
 * method to the BaseImpl instance, reusing its implementation without code duplication.
 */
interface Base {
    fun print()
}

class BaseImpl(
    val x: Int,
) : Base {
    override fun print() {
        println("BaseImpl $x")
    }
}

class Derived(
    b: Base,
) : Base by b // Delegation

fun main() {
    val b = BaseImpl(10)
    val d = Derived(b)
    d.print() // Output: BaseImpl 10
}
