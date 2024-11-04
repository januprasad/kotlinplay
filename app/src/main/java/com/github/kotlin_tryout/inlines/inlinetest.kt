package com.github.kotlin_tryout.inlines

fun main() {
    someCallback {
        println("More....$it")
    }
    println("Im gonna miss!")
}

inline fun someCallback(noinline func: (String) -> Unit) {
    someotherFn {
        return@someotherFn
    }
}

fun someotherFn(func: (String) -> Unit) {
    func("hello")
}
