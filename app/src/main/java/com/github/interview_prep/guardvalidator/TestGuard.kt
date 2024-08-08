package com.github.interview_prep.guardvalidator


fun main() {
    val key1 = "input was not an integer"
    val input1: String? = "=2000120"
//    val input2: String? = null
//    val input3: String? = "232323"
    println(validator(input1, key1))
//    println(validator(input2, key1))
//    println(validator(input3, key1))
//    val input: String? = "=2000120"
//    input?.toIntOrNull()?.let {
//        println("result: ${100 * it}")
//    }
}

fun validator(input: String?, key: String): String {
    val result = input.toInt2(key)
    println("result: ${100 * result}")
    return "Success"
}

//fun validator(input: String?, key: String): String {
//    try {
//        val result = input?.toIntOrNull()
//        println("result: ${100 * result!!}")
//    } catch (e: Exception) {
//        return key
//    }
//    return "Success"
//}

fun String?.toInt2(message: String): Int {
    return this?.toIntOrNull() ?: edgeCaseHandled { message }
}

fun <T> edgeCaseHandled(message: () -> String): T {
    throw ValidationError(error = message())
}

class ValidationError(val error: String) : Exception() {
    override fun toString(): String {
        return error
    }
}