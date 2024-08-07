package com.github.interview_prep.associatedBy

fun main() {
    val map =
        listOf(
            "a" to 1,
            "b" to 2,
            "c" to 3,
        )

    println(map.associateBy { it.first })
}
