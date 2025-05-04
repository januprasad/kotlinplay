package com.github.crypto

class MyClass {
    val PUSH = 10
    val DEEPLINK = 12
    fun test(num: Int) {

        if (num == PUSH) {
            println("executing push")
            return
        }
        if (num == DEEPLINK) {
            println("executing deeplink")
            return
        }
        println("executing normal call")
    }
}

fun main() {
    MyClass().test(10)
    MyClass().test(12)
    MyClass().test(13)
}