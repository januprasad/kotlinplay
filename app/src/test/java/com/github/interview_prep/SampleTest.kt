package com.github.interview_prep

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SampleTest {
    @Test
    fun myRepositoryTest() =
        runTest {
            // Given a repository that combines values from two data sources:
            val repository = MyRepository()

            // When the repository emits a value
            val firstItem = repository.counter.first() // Returns the first item in the flow

            // Then check it's the expected item
            assertEquals(ITEM_1, firstItem)
        }
}
