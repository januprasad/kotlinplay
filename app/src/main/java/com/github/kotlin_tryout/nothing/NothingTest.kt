package com.github.kotlin_tryout.nothing

fun main() {
    val nothing = failed()
    // println(nothing)
    println("nothing")
}

fun failed(): Nothing = throw RuntimeException("failed")

// fun getStringValue(): String = noReturn()
//
// fun getIntValue(): Int = noReturnUnit()
//
// fun noReturn(): Nothing {
//    while (true) {
//    }
// }
//
// fun noReturnUnit() {
//    while (true) {
//    }
// }
