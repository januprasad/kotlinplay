package com.github.interview_prep

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MyViewModelMockitoTests {
    private lateinit var myViewModel: MyViewModel

    @Mock
    lateinit var mobula: Mobula

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() = Dispatchers.setMain(StandardTestDispatcher())

    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `Given the sut is initialized, then it waits for event`() {
        val sut = MyViewModel()
        assertTrue(sut.uiState.value == AppState.Loading)
    }
}
