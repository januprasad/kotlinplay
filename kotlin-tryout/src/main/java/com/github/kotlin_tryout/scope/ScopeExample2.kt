package com.github.kotlin_tryout.scope

fun main() {
    val junkie = Junkie("Junkie", 22)
    junkie.let {
        it.name = "Junkie2"
    }
    println(junkie)

    val userid =
        junkie.let {
            it.name.uppercase() + System.currentTimeMillis()
        }
    println(userid)

    with(junkie) {
        callapi(this)
        process(this)
        age
    }
    val c =
        junkie.apply {
            name = "Junkie23"
        }
    val b =
        junkie.run {
            name = "Junkie23"
        }
    val x =
        junkie
            .let {
                val name = "Junki33e23"
            }.also {
                println(it)
            }
    val y =
        junkie.also {
            val name = "Junkie23"
        }

    println(junkie)
    greet("333")
}

fun process(junkie: Junkie) {
}

fun callapi(junkie: Junkie) {
}

data class Junkie(
    var name: String,
    val age: Int,
)

/**
 * public inline fun <T, R> T.let(block: (T) -> R): R {
 *     contract {
 *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
 *     }
 *     return block(this)
 * }
 * public inline fun <T> T.also(block: (T) -> Unit): T {
 *     contract {
 *         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
 *     }
 *     block(this)
 *     return this
 * }
 *
 *
 */
sealed class InvocationKind {
    object A : InvocationKind()
}

fun InvocationKind.A.push() {
    println("Hello")
}

fun greet(name: String): kotlin.Unit {
    InvocationKind.A.push()
}
