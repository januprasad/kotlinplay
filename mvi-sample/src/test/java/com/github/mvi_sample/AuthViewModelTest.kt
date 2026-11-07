package com.github.mvi_sample

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AuthViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given initial state then not authenticated`() = runTest(testDispatcher) {
        viewModel.state.test {
            assertEquals(AuthState.NotAuthenticated, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when StartAuthentication intent is sent, state becomes Authenticating`() = runTest(testDispatcher) {
        viewModel.state.test {
            assertEquals(AuthState.NotAuthenticated, awaitItem())

            viewModel.onIntent(AuthIntent.StartAuthentication)

            assertEquals(AuthState.Authenticating, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
