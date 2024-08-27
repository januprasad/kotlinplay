package com.github.interview_prep.dsl

import com.github.interview_prep.comparableexample.Person

fun person(block: Person.() -> Unit): Person =
    Person("JK", 33)
        .apply(block)

fun main() {
    val android =
        androidOS {
            name = "JellyBean"
            version = "4.1.2"
        }
    println(android)
}

fun os(block: Android.() -> Unit): Android = Android().apply(block)

fun androidOS(block: Android.() -> Unit) = Android().apply(block)

data class Android(
    var name: String = "",
    var version: String = "",
)
