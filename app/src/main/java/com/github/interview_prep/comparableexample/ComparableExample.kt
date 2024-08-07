package com.github.interview_prep.comparableexample

data class Person(
    val name: String,
    val age: Int,
) : Comparable<Person> {
    override fun compareTo(other: Person): Int = name.compareTo(other.name)
}

fun main() {
    val person =
        listOf(
            Person(
                "Kitten",
                44,
            ),
            Person(
                "John",
                23,
            ),
            Person(
                "Amanda",
                12,
            ),
            Person(
                "Miya",
                33,
            ),
        )

    val sortedPeople = person.sorted()
    println(sortedPeople)
}
