package com.github.interview_prep.extensions

class User(
    val name: String,
) {
    companion object
}

class UserRecord(
    val firstName: String,
    val lastName: String,
)

operator fun User.Companion.invoke(record: UserRecord) = User(record.firstName + record.lastName)

fun main() {
    val user = User(UserRecord("John", "Doe"))
    println(user.name) // prints: JohnDoe
}
