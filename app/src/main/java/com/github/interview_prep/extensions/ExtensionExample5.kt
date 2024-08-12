package com.github.interview_prep.extensions

class User(
    val name: String,
) {
    companion object
}

class User2(
    val name: String,
) {
    constructor(record: UserRecord) : this(record.firstName + record.lastName)
}

class UserRecord(
    val firstName: String,
    val lastName: String,
)

operator fun User.Companion.invoke(record: UserRecord) = User(record.firstName + record.lastName)

operator fun User.Companion.invoke(fullName: Pair<String, String>) = User(fullName.first + fullName.second)

operator fun Int.Companion.invoke(value: String) = value.toInt()

fun User(fullName: Pair<String, String>) = User(fullName.first + fullName.second)

fun main() {
    val user = User(UserRecord("John", "Doe"))
    val user2 = User2(UserRecord("John", "Doe"))
    val user3 = User(Pair("A", "B"))
    println(user.name) // prints: JohnDoe
    println(user2.name) // prints: JohnDoe
    println(user3.name) // prints: JohnDoe
    println("4" + "4") // prints: 5
}
