package com.github.interview_prep.scope

fun main() {
    val junkie = Junkie("Junkie")
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
    }
    junkie.apply {
        name = "Junkie23"
    }
    println(junkie)
}

fun process(junkie: Junkie) {
}

fun callapi(junkie: Junkie) {
}

data class Junkie(
    var name: String,
)
