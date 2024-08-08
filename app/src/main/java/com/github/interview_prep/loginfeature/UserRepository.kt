package com.github.interview_prep.loginfeature

class UserRepository {
    data class LoginResponse(
        val response: String,
        val accessToken: String = "token",
    )

    fun login(
        username: String,
        password: String,
    ): LoginResponse = LoginResponse("Success")

    fun getUser(id: String): User? = User("12", "Alice")
}
