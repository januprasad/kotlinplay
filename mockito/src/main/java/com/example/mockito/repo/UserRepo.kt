package com.example.mockito.repo

import com.example.mockito.model.User

class UserRepo {
    val users =
        listOf(
            User(
                name = "John",
                email = "john@example.com",
                id = "1",
            ),
            User(
                name = "Mary",
                email = "mary@example.com",
                id = "2",
            ),
            User(
                name = "Michael",
                email = "micheal@example.com",
                id = "1",
            ),
        )

    fun login(
        username: String,
        id: String,
    ): LoginStatus {
        val user = users.firstOrNull { it.email == username && it.id == id }
        return if (user != null) {
            LoginStatus.SUCCESS
        } else {
            LoginStatus.USER_NOT_FOUND
        }
    }
}

enum class LoginStatus {
    SUCCESS,
    USER_NOT_FOUND,
}
