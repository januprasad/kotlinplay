package com.github.solid

fun main() {
    val fruits = listOf("Apple","Orange","Banana","PineApple")
    fruits.forEach {
//        println(it)
    }
    val fruits2 = buildList {
        add("Berries")
        addAll(fruits)
        add("Apple")
        add("Apple")
    } + "Apple"

    println(fruits2 - "Apple")
}