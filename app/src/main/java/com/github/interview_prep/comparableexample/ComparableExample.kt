package com.github.interview_prep.comparableexample

data class Person(
    var name: String,
    var age: Int,
) : Comparable<Person> {
    override fun compareTo(other: Person): Int = name.compareTo(other.name)
}

/**
 * Defines a natural ordering for objects of a class.
 * A class implements the Comparable interface to provide a comparison logic for its instances.
 * The compareTo method is used to compare two objects.
 * Provides a single sorting sequence
 *call in sorted method, so it will trigger
 */

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
