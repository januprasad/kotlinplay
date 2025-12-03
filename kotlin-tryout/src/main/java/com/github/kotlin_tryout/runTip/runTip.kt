package com.github.kotlin_tryout.runTip

fun main() {
    val name: String? = null
    val name2: String =
        name ?: run {
            println("we init name here")
            "default"
        }

    println(name2)
}
