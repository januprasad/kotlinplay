package com.github.interview_prep.reified

import kotlin.reflect.full.isSubclassOf

fun main() {
    val red = getEnumValue<Color>(Color.RED.name)
    val green = getEnumValue<Color>(Color.GREEN.name)
    val unknown = getEnumValueNullable<Color>(Color.GREEN.name.drop(1))
    println(red) // Output: RED
    println(green) // Output: GREEN
    println(unknown) // Output: null

    println(Configuration.get<String>("Abc"))
    println(Configuration.get<Color>(Color.GREEN.name))
}

enum class Color {
    RED,
    GREEN,
    BLUE,
    UNDEFINED,
}

inline fun <reified T : Enum<T>> parse(name: String): T = enumValueOf<T>(name)

inline fun <reified T : Enum<T>> getEnumValueNullable(name: String): T? =
    try {
        parse<T>(name) // Retrieves the enum constant of the specified name
    } catch (e: IllegalArgumentException) {
        null // Returns null if the name doesn't match any enum constants
    }

inline fun <reified T : Enum<T>> getEnumValueNonNullable(name: String): T? =
    try {
        enumValueOf<T>(name) // Retrieves the enum constant of the specified name
    } catch (e: IllegalArgumentException) {
        null // Returns null if the name doesn't match any enum constants
    }

inline fun <reified T : Enum<T>> getEnumValue(name: String): T =
    T::class.isSubclassOf(Enum::class).let {
        @Suppress("UPPER_BOUND_VIOLATED")
        enumValueOf<T>(name) // Retrieves the enum constant of the specified name
    }

//
object Configuration {
    inline fun <reified T> get(propertyName: String): T =
        when {
//            T::class.isSubclassOf(Enum::class) -> {
//                @Suppress("UPPER_BOUND_VIOLATED")
//                enumValueOf<T>(propertyName)
//            }
            T::class == String::class -> {
                // Convert to String (if it's already a String, just return it)
                propertyName as T
            }

            T::class == Int::class -> {
                // Convert to Int
                propertyName.toIntOrNull() as T?
                    ?: throw IllegalArgumentException("Cannot convert '$propertyName' to Int")
            }

            else -> {
                throw IllegalArgumentException("Unsupported type")
            }
        }
}
