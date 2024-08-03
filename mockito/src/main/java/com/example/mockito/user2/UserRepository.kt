package com.example.mockito.user2

import com.example.mockito.model.User

interface UserRepository {
    fun getUserById(userId: String): User
}

class UserService(
    private val userRepository: UserRepository,
) {
    fun getUserDetails(userId: String): User? = userRepository.getUserById(userId)
}
