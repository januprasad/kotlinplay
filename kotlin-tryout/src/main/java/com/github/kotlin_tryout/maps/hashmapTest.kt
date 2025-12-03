package com.github.kotlin_tryout.maps

fun main() {
    val map = mapOf(null to null, null to null, null to null, null to null)
    val pair = Pair(null, null)
    val list = listOf(null)
//    println(map)
//    println(pair)
//    println(list)
    val arr = listOf(1, listOf(1, 2), listOf(4, 5))

    val numbers = listOf(1, 2, 3, 4, 5)
    println(5 in numbers)

    val prod =
        numbers.fold(1) { acc, i ->
            acc * i
        }

    val people = listOf(Person("Alice", 30), Person("Bob", 25), Person("Charlie", 35))
    val animals = listOf("raccoon", "reindeer", "cow", "camel", "giraffe", "goat")
    val compareByVowelCount = compareBy { s: String -> s.count { it in "raccoon" } }
    val maxVowels = animals.groupingBy { it.first() }.reduce { _, a, b -> maxOf(a, b, compareByVowelCount) }
    println(maxVowels) // {r=reindeer, c=camel, g=giraffe}
    val sum: Int = numbers.reduce { acc, next -> acc + next }
}

data class Person(
    val name: String,
    val age: Int,
)
