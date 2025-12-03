package com.github.kotlin_tryout.reified

fun main() {
    val sms = getEnumValueNullable1<Message>("SMS")
    val sms1 = enumValueOf<Message>("SMS1") // Re
    println(sms1) // Output: null
}

inline fun <reified T : Enum<T>> getEnumValueNullable1(name: String): T? =
    try {
        enumValueOf<T>(name) // Retrieves the enum constant of the specified name
    } catch (e: IllegalArgumentException) {
        null // Returns null if the name doesn't match any enum constants
    }

enum class Message {
    SMS,
    MMS,
}
