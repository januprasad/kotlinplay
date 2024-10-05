package com.example.problem_solving_questions

fun main() {
    val users: List<User> =
        listOf(
            User("John Smith", 39, Gender.MALE),
            User("Jennifer Lawrence", 35, Gender.FEMALE),
            User("Jack Johnson", 27, Gender.MALE),
            User("Robert Downey", 51, Gender.MALE),
            User("Jessica Fletcher", 63, Gender.FEMALE),
        )

//    val usersByGender = users.groupBy { user -> user.gender }
//    println(usersByGender)

    println(users.filter(Gender.FEMALE))
}

inline fun List<User>.filter(type: Gender): List<User> = this.filter { it.gender == type }

data class User(
    val name: String,
    val age: Int,
    val gender: Gender,
)

enum class Gender {
    FEMALE,
    MALE,
}
