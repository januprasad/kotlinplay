package com.github.kotlin_tryout.decoratorpattern

// fun main() {
//    val map =
//        mapOf(
//            "name" to "John Doe",
//            "age" to 30,
//            "city" to "New York",
//        )
//
//    val gson = GsonBuilder().setPrettyPrinting().create()
//    val jsonString = gson.toJson(map)
//
//    println(jsonString)
//
//    val gson1 = GsonBuilder().setPrettyPrinting().create()
//    val jsonBuilder = JsonBuilder()
//    val jsonString1 = gson.toJson(jsonBuilder.emptyMap)
//    println(jsonString1)
// }

val jsonMaker =
    JsonObjectify { key, value ->
        println(key + ": " + value)
    }

fun interface JsonObjectify {
    fun jsonPrint(
        key: String,
        message: String,
    )
}

fun main() {
    val map =
        mapOf(
            "name" to "John Doe",
            "age" to 30,
            "city" to "New York",
        )

    jsonMaker.jsonPrint("name", map["name"] as String)
    jsonMaker.jsonPrint("age", map["age"].toString())
    jsonMaker.jsonPrint("city", map["city"] as String)
}
