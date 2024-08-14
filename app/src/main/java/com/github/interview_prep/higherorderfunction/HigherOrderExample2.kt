package com.github.interview_prep.higherorderfunction

fun main() {
    val result3 = calculate(2, 3, ::pow)

    println(
        calculate(2, 3) { one, two ->
            (pow(one, two))
        },
    )
//    println("Power: $result3") // Output: Multiplication: 50
}

fun pow(
    x: Int,
    y: Int,
) = Math.pow(x.toDouble(), y.toDouble()).toInt()
