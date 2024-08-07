package com.github.interview_prep

import com.google.gson.Gson

fun main() {
    val account = Account("123", "John Doe", 30)
    val accountJson = Gson().toJson(account)
//    println(isInstanceOf<Account>(account))
    val account1 = accountJson.toKotlinObject<Account>()
    println(account1.name)
}

inline fun <reified T : Any> String.toKotlinObject(): T {
    val json = "{\"id\":\"123\",\"name\":\"John Doe\",\"age\":30}"
    val gson = Gson()
    return gson.fromJson(json, T::class.java)
}

inline fun <reified T> isInstanceOf(obj: Any): Boolean = obj is T

data class Account(
    val id: String,
    val name: String,
    val age: Int,
)

/**
 * to call from java
 * fun <T> isInstanceOf(obj: Any, clazz: Class<T>): Boolean {
 *     return clazz.isInstance(obj)
 * }
 */
