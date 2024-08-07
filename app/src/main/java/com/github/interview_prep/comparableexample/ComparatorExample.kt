package com.github.interview_prep.comparableexample

import com.github.interview_prep.Student

fun main() {
    val students =
        listOf(
            Student("John", 1),
            Student("Anne", 2),
            Student("Zyed", 3),
            Student("Rixen", 4),
        )

    val comparator: Comparator<Student> = compareBy { it.name }
    val nameThenAgeComparator: Comparator<Student> = compareBy<Student> { it.name }.thenBy { it.age }
    val sortedPeople = students.sortedWith(comparator)
    val sortedPeopleNameAge = students.sortedWith(nameThenAgeComparator)
    val sortedAge = students.sortedBy { it.age }
    val sortedName = students.sortedByDescending { it.name }

    println(sortedAge)
    println(sortedName)
    println(sortedPeople)
    println(sortedPeopleNameAge)
}
