package com.example.mockito.user

import com.example.mockito.repo.LoginStatus
import com.example.mockito.repo.UserRepo
import com.example.mockito.service.UserService
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {
    @Mock
    lateinit var userRepository: UserRepo

    @Before
    fun setUp() {
        // Initialize mockito
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testLoginSuccess() {
        Mockito.`when`(userRepository.login(anyString(), anyString())).thenReturn(LoginStatus.SUCCESS)
        val userService = UserService(userRepository)
        TestCase.assertEquals("User logined successfully", userService.loginUser("aswe", "wewew"))
    }

    @Test
    fun testLoginFailed() {
        Mockito.`when`(userRepository.login(anyString(), anyString())).thenReturn(LoginStatus.USER_NOT_FOUND)
        val userService = UserService(userRepository)
        TestCase.assertEquals("User not found", userService.loginUser("aswe", "wewew"))
    }
}
