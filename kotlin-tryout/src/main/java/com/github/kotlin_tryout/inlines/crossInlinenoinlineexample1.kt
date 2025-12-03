package com.github.kotlin_tryout.inlines

fun doSomething1(result: (String) -> Unit) {
    println("do doSomething1")
    result("result1")
}

fun doSomething2(result: (String) -> Unit) {
    println("do doSomething2")
    result("result2")
}

inline fun noinlineExample(
    noinline result: (String) -> Unit,
    noinline result2: (String) -> Unit,
) {
    doSomething1(result)
    /**
     * trying to pass another function from inline and execute
     */
    doSomething2 {
        result2(it)
    }
}

fun main(args: Array<String>) {
    noinlineExample(
        result = { println(it) },
        result2 = { println(it) },
    )
}

/**
 * Feature	-> noinline	crossinline
 * Inlining	-> Prevents inlining of the lambda	Prevents inlining of the lambda
 * Capturing variables	-> Can capture variables from the outer scope	Cannot capture variables from the outer scope
 * Use cases ->	When performance is critical or lambda complexity prevents inlining	When passing lambdas to other functions or when lambda behavior should be independent of the outer scope
 * Restrictions	-> None beyond preventing inlining	Cannot access variables from the outer scope
 */
