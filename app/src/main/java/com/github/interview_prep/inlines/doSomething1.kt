package com.github.interview_prep.inlines

fun doSomething1() {
    println("do something with inline")
}

fun doSomething2(s: String) {
    println("do something with noinline")
}

inline fun noinlineExample(
    something1: () -> Unit,
    crossinline something2: (String) -> Unit,
) {
    something1.invoke()
    something2.invoke("")
}

fun main(args: Array<String>) {
    noinlineExample(
        ::doSomething1,
        ::doSomething2,
    )
}
