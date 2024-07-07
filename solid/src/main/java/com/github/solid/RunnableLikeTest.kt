package com.github.solid

fun main() {
    val runnableExample =
        RunnableV2 { TODO("Not yet implemented") }
    runnableExample.handler()
}

private fun interface RunnableV2 {
    fun handler()
}
