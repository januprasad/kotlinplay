package com.github.interview_prep.inlines

fun main() {
    executeFirst(
        func2 = {
            println("func2")
        },
        func = {
            println("func1")
        },
    )
}

inline fun executeFirst(
    noinline func: () -> Unit,
    crossinline func2: () -> Unit,
) {
    executeCallbackParamterNoInline(func)
    executeCallbackCrossInline {
        func2()
    }
    println("Hello")
}

fun executeCallbackParamterNoInline(func2: () -> Unit) {
    func2()
}

fun executeCallbackCrossInline(func: () -> Unit) {
    func()
}
