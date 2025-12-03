package com.github.kotlin_tryout.inlines

fun main() {
    someCallback {
        println("More....$it")
    }
    println("Im gonna miss!")
    printSomething {
        println(name)
    }
}

inline fun someCallback(noinline func: (String) -> Unit) {
    someotherFn {
        return@someotherFn
    }
}

fun someotherFn(func: (String) -> Unit) {
    func("hello")
}
lateinit var name : String
inline fun printSomething(lambda: () -> Unit) {
    println("Hi")
    lambda()
    name = "JK"
}