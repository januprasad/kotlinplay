package com.github.interview_prep.delgates

import kotlin.properties.Delegates

fun main() {
    var name: String by Delegates.observable("") { _, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }
    name = "JK"
    name = "JK2"
    name = "JK3"
    println(name.reversed())
}
