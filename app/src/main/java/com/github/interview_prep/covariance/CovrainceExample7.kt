package com.github.interview_prep.covariance

fun main() {
    var anyList: List<Any> = mutableListOf("1", "2") // why is MutableList covariant?
    var intList: List<Int> = mutableListOf(1, 2)
    anyList = intList
}
