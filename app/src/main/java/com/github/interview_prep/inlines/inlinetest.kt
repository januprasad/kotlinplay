package com.github.interview_prep.inlines

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
