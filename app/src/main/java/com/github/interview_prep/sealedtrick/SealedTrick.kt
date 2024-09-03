package com.github.interview_prep.sealedtrick

fun main() {
//    sealedValueOf<Result>("propertyName")
}

// inline fun <reified T> sealedValueOf(name: String): T {
//    @Suppress("UNCHECKED_CAST")
//    return when (name) {
//        "Success" -> Result.Success(22)
//        else -> throw IllegalArgumentException("No matching enum constant for name: $name")
//    }
// }

sealed class Result {
    data class Success(
        val data: Int,
    ) : Result()

    data class Failure(
        val error: Throwable,
    ) : Result()
}
