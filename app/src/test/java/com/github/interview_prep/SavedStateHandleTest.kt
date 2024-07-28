package com.github.interview_prep

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SavedStateHandleTest {
    @Test
    fun `whatever`() {
        runTest {
            val savedStateHandle =
                SavedStateHandle().apply {
                    set("somevalue", "something")
                }
//            val viewModel = PostListViewModel(coroutineScope, getPostsUseCase, savedStateHandle)
        }
    }
}
