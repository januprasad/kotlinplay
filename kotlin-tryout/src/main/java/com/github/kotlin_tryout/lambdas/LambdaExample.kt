package com.github.kotlin_tryout.lambdas

fun main() {
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println(sum.invoke(2, 3))
    println(UInt)
    val num: UInt = 232u
    println(num)
    println(Utils.logger)
    val executer =
        object : Executer {
            override fun exec(): String = "Executed"
        }
    println(executer.exec())
}

object Utils {
    val logger = "logger"
}

interface Executer {
    fun exec(): String
}
