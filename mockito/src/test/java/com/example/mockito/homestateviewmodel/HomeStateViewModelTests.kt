package com.example.mockito.homestateviewmodel

import com.example.mockito.viewmodel.HomeStateViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeStateViewModelTests {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() = Dispatchers.setMain(StandardTestDispatcher())

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `loadItems testing`() {
        // Arrange
        val homeStateViewModel = HomeStateViewModel()

        // Act
        homeStateViewModel.loadItems()

        // Assert
        // Check if the data is loaded correctly
        assertEquals(homeStateViewModel.appState.loaded, true)
    }

    @Test
    fun `loadSomeItems testing`() = runTest {
        // Arrange
        val homeStateViewModel = HomeStateViewModel()
        // Act
        homeStateViewModel.loadSomeItems()
        advanceUntilIdle()

        // Assert
        // Check if the data is loaded correctly
        assertEquals(homeStateViewModel.appState.loaded, true)
    }
}