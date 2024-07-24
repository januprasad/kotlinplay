package com.github.stateflow_sharedflow

fun main() {
    val hasZero = printWhileNotZero(listOf(1, 2, 3, 0, 4, 5))
    println(hasZero)
}

fun printWhileNotZero(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) {
            // non-local return
            return true // returns from printWhileNotZero, allowed because forEach is inlined
        }

        println(it)
    }

    ints.doAction {
        if (it == 0) {
            // non-local return
            return true // returns from printWhileNotZero, prohibited because doAction isn't inlined
        }
        println(it)
    }

    return false
}

inline fun List<Int>.doAction(action: (Int) -> Unit) {
    for (i in this) action(i)
}
