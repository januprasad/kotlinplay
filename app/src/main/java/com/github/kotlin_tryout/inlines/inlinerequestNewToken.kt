package com.github.kotlin_tryout.inlines

fun main() {
    requestNewToken(
        false,
        onRefresh = {
            println("new token")
        },
        onGenerate = {
            println("obtained token")
        },
    )
}

inline fun requestNewToken(
    hasToken: Boolean,
    crossinline onRefresh: () -> Unit,
    noinline onGenerate: () -> Unit,
) {
    if (hasToken) {
        httpCall("google.com/get-token", onGenerate) // We must use
        // noinline to pass function as an argument to a
        // function that is not inlined
    } else {
        httpCall("google.com/refresh-token") {
            onRefresh() // We must use cross inline to
            // inline function in a context where
            // non-local return is not allowed
            onGenerate()
        }
    }
}

fun httpCall(
    url: String,
    callback: () -> Unit,
) {
    println("fetching new token from $url")
    callback()
}
