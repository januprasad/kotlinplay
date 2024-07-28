package com.example.saved_state_handle

import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: SavedStateVM

    @Before
    fun setup() {
        val mockedList = listOf("1", "2", "3")
        val valuesMap =
            mapOf(
                "savedUiState" to bundleOf("value" to mutableStateOf(mockedList)),
            )

        val savedState = SavedStateHandle(valuesMap)

        viewModel = SavedStateVM(savedState = savedState)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(viewModel.filteredData.first(), "1")
    }
}
