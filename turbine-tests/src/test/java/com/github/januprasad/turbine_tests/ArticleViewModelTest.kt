package com.github.januprasad.turbine_tests

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleViewModelTest {

    @Mock
    lateinit var dataRepository: DataRepository

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: ArticleViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ArticleViewModel(dataRepository, testDispatcher)
    }

    @Test
    fun `given initial state then waiting`() = runTest(testDispatcher) {
        viewModel.state.test {
            assertEquals(ArticleState.Waiting, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when loadData is called then state transitions to running and loaded`() = runTest(testDispatcher) {
        val expected = "Blog Turbine Data"
        dataRepository.stub {
            onBlocking { fetchData() } doAnswer { expected }
        }

        viewModel.state.test {
            assertEquals(ArticleState.Waiting, awaitItem())

            viewModel.loadData()

            // Verify work is queued (nothing emitted yet on StandardTestDispatcher)
            expectNoEvents()

            // Run queued work via sync point
            runCurrent()

            assertEquals(ArticleState.Running, awaitItem())
            assertEquals(ArticleState.Loaded(expected), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}
