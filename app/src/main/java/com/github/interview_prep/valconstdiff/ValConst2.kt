package com.github.interview_prep.valconstdiff

import kotlin.random.Random

fun main() {
    val random = Random(1234).nextInt()
    val value = DummyHolder(random)
    println(value)
    println(DummyValues.filename)
    println(DummyValues.filename2)
}

data class DummyHolder(
    val int: Int,
)

object DummyValues {
    const val extension = "png"
    val filename
        get() = "${System.currentTimeMillis()}" + ".$extension"

    val filename2 by lazy {
        "${System.currentTimeMillis()}"
    }
}
