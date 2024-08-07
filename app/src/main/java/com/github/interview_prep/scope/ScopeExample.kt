package com.github.interview_prep.scope

import com.github.interview_prep.reified.Student

data class School(
    var student: Student,
)

fun main() {
    var school: School = School(Student("JK"))

    var s =
        Student(
            name = "John",
        )

    s.apply {
        this.name = "John22"
    }

    s.run {
        this.name = "John22"
    }

    with(school) {
        school.student.apply {
            this.name = "John22"
        }
    }
    println(school)

    s.also {
        it.name = "John22" // same as above
    }

    s.let {
        it.name = "Anugraha22" // same as above
    }
    println(s)
}
