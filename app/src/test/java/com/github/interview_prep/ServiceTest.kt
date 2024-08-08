package com.github.interview_prep

import com.github.interview_prep.loginfeature.User
import com.github.interview_prep.loginfeature.UserRepository
import com.github.interview_prep.loginfeature.UserService
import io.mockk.*
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserServiceTest {
    private val userRepository = mockk<UserRepository>()
    private val userService = UserService(userRepository)

    @Before
    fun setup() {
        // Define behavior of userRepository mock
        every { userRepository.getUser("1") } returns User("1", "Alice")
    }

    @Test
    fun testGetUser() {
        // Call getUser method on userService
        val user = userService.getUser("1")
        // Verify that userRepository's getUser method was called with the correct argument
        verify { userRepository.getUser("1") }
        // Verify that the correct user object was returned
        assertEquals(User("1", "Alice"), user)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
