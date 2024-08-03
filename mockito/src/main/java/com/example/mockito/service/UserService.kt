package com.example.mockito.service

import com.example.mockito.repo.LoginStatus.*
import com.example.mockito.repo.UserRepo

class UserService(
    private val userRepo: UserRepo,
) {
    fun loginUser(
        username: String,
        id: String,
    ): String {
        val status = userRepo.login(username, id)
        return when (status) {
            SUCCESS -> "User logined successfully"
            USER_NOT_FOUND -> "User not found"
        }
    }
}
