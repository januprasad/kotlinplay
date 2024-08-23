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

    val os =
        s.run {
            school.student.name
            this
        }

    val name =
        with(school) {
            println(student)
        }

    school.student.apply {
        this.name = "John22"
    }

    println(school)

    val s1 =
        s
            .also {
                it.name = "2222" // same as above
            }.also {
                println(it.name)
            }

    println(s1)

    s.let {
        it.name = "Anugraha22" // same as above
    }
    println(s)

    val person = Student("Charlie")

    // Create a new person with the same name and age + 5 using run
    val newPerson =
        person.run {
            Student(this.name.uppercase())
        }
    println(newPerson)
}
