package com.github.interview_prep.loginfeature

class UserService(
    private val userRepository: UserRepository,
) {
    fun getUser(id: String): User? = userRepository.getUser(id)
}
