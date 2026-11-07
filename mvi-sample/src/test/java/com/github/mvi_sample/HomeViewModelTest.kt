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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getItemsUseCase: GetItemsUseCase = org.mockito.kotlin.mock()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getItemsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when LoadItems succeeds, state contains items`() = runTest(testDispatcher) {
        val items = listOf(Item("1", "Test Item"))
        whenever(getItemsUseCase.invoke()).doReturn(Result.success(items))

        viewModel.state.test {
            assertEquals(HomeState(), awaitItem()) // Initial state

            viewModel.onIntent(HomeIntent.LoadItems)

            assertTrue(awaitItem().isLoading)
            val loadedState = awaitItem()
            assertEquals(false, loadedState.isLoading)
            assertEquals(items, loadedState.items)
        }
    }
}
