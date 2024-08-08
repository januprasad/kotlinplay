package com.github.interview_prep.zipoperation

fun main() {
    val names = listOf("JK", "Achu", "Baby")
    val age = listOf(34, 23, 6, 4, 55, 63, 43)
    val map = names.zip(age) { name, age -> "${name.uppercase()}" to "${age + 1}" }
    println(map)
    val (o, e) = age.partition { it % 2 == 0 }
    println(o)
    println(e)
    val map1 = age.zipWithNext { a, b -> "${a * 2}" to "${b * 2}" }
    println(age)
    println(map1)
    val age2 = arrayOf(7, 9, 9, 9, 6)
    val map2 = names.zip(age2) { name, age -> "${name.uppercase()}" to "${age + 1}" }
    println(map2)
}
