package com.github.stateflow_sharedflow

fun main() {
//        println(add1(3,4))
//        println(add2(3,4))
    val modAdd = addfn(3, 4)
    println(modAdd(4))
    println(another(3)("Hello"))
    val x = another(3).invoke("")
}

fun addfn(x: Int, y: Int): (Int) -> Int = { sum -> sum + x + y }
fun another(x: Int): (String) -> String {
    return { "Hello" }
}

