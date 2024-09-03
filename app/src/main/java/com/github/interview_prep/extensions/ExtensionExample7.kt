package com.github.interview_prep.extensions

data class Boogiman(
    val name: String,
)

interface Jumbo

class JubJumbo : Jumbo {
}

fun Jumbo.isJumbo() = println("Hi")

fun main() {
    val boogiman = JubJumbo()
    boogiman.isJumbo()
}
