package com.github.kotlin_tryout.mapExample

data class Student(val name: String, val age: Int, val grade: String)

data class CollegeStudent(val name: String, val age: Int, val collegeName: String)

fun main() {
    val students = listOf(
        Student("Alice", 10, "5"),
        Student("Bob", 11, "6"),
        Student("Charlie", 9, "4")
    )

    // You can now use the 'students' list
    for (student in students) {
        println("Name: ${student.name}, Age: ${student.age}, Grade: ${student.grade}")
    }

    val collegeStudents = students.map(::toCollegeStudent)

    println("\nCollege Students:")
    for (collegeStudent in collegeStudents) {
        println("Name: ${collegeStudent.name}, Age: ${collegeStudent.age}, College: ${collegeStudent.collegeName}")
    }
}

fun toCollegeStudent(student: Student): CollegeStudent {
    return CollegeStudent(student.name, student.age + 7, "Android University") // Just an example transformation
}
