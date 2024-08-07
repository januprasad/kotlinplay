package com.github.interview_prep.runTip

fun main() {
    val name: String? = null
    val name2: String =
        name ?: run {
            println("we init name here")
            "default"
        }

    println(name2)
}
