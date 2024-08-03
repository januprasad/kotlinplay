package com.example.mockito.user2

import com.example.mockito.model.User

class UserRepositoryImpl : UserRepository {
    override fun getUserById(userId: String): User =
        User(
            userId,
            "",
            "",
        )
}
