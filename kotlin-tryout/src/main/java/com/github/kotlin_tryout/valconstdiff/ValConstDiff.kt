package com.github.kotlin_tryout.valconstdiff

data class Dummy(
    val name: String,
)

fun main() {
    val version = 12344344444
    val dummy = Dummy("John Doe")
    println(dummy)
    println(version)
    println(Constants.VERSION)
}

object Constants {
    const val VERSION = 23246666666
}
