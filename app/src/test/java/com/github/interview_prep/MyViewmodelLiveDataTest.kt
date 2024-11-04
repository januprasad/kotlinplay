package com.github.kotlin_tryout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyViewModelTest {
    // Rule to execute LiveData synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MyViewModel

    @Before
    fun setUp() {
        viewModel = MyViewModel()
    }

    @Test
    fun `test initial counter value is zero`() {
        // Use an Observer to check the initial value
        val observer = Observer<Int> {}
        try {
            viewModel.counter.observeForever(observer)
            assertEquals(0, viewModel.counter.value)
        } finally {
            viewModel.counter.removeObserver(observer)
        }
    }

    @Test
    fun `test counter increments correctly`() {
        val observer = Observer<Int> {}
        try {
            viewModel.counter.observeForever(observer)
            viewModel.increment()
            assertEquals(1, viewModel.counter.value)

            viewModel.increment()
            assertEquals(2, viewModel.counter.value)
        } finally {
            viewModel.counter.removeObserver(observer)
        }
    }
}
