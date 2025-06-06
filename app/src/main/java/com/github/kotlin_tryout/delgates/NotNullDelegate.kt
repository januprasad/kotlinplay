package com.github.kotlin_tryout.delgates

import kotlin.properties.Delegates

fun main() {
    NotNullDelegate().printMax()
}

class NotNullDelegate {
    private var max: Int by Delegates.notNull()

    fun printMax() {
        max = 10
        println(max)
    }
}
