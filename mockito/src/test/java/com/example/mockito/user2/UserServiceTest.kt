package com.example.mockito.user2

import com.example.mockito.model.User
import io.mockk.*
import junit.framework.TestCase
import org.junit.Test

class UserServiceTest {
    @Test
    fun `should call userRepository with correct userId`() {
        val userRepository = spyk(UserRepositoryImpl())
        val userService = UserService(userRepository)

        userService.getUserDetails("123")

        verify(exactly = 1) { userRepository.getUserById("123") }
    }

    @Test
    fun `should call userRepository with correct userI 2`() {
        val userRepository = mockk<UserRepositoryImpl>()
        val userService = UserService(userRepository)
        coEvery { userRepository.getUserById("123") } returns User("123", "", "")

        val user = userService.getUserDetails("123")
        TestCase.assertEquals("123", user?.id)
        verify { userRepository.getUserById("123") }
    }
}
