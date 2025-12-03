package com.github.kotlin_tryout.reified

fun main() {
    val jk: SchoolData = Student("jk")
    if (isOfType<Student>(jk)) {
        println("jk is Student")
    }
    if (isOfType<SchoolData>(jk)) {
        println("jk is Student")
    }
    val stM = StudentType.Male
    val stF = StudentType.Female
    if (isOfType<StudentType.Male>(stM)) {
        println("Male")
    }
    if (isOfType<StudentType.Female>(stF)) {
        println("FMale")
    }
    if (isOfType<StudentType>(stF)) {
        println("FMale")
    }
}

sealed class StudentType {
    object Male : StudentType()

    object Female : StudentType()
}

data class Student(
    var name: String,
) : SchoolData

interface SchoolData

inline fun <reified T> isOfType(value: Any): Boolean = value is T

inline fun <reified T> cast(value: Any): T? = value as? T
