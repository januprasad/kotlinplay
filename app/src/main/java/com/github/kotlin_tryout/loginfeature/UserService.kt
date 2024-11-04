package com.github.kotlin_tryout.loginfeature

class UserService(
    private val userRepository: UserRepository,
) {
    fun getUser(id: String): User? = userRepository.getUser(id)
}
