package com.example.problem_solving_questions

fun main() {
    val result =
        "perm1|0,perm2|2,perm2|1"
            .split(",")
            .map {
                val split = it.split("|")
                split[0] to split[1].toLong()
            }.groupBy({ it.first }, { it.second })
            .mapValues { it.value.toSortedSet() }

    println(result)
}
