package com.github.interview_prep

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestableServiceTestCases {
    @Test
    fun testTestableService() {
        // given
        val service = mockk<TestableService>()
        every { service.getDataFromDb("Expected Param") } returns "Expected Output"

        // when
        val result = service.getDataFromDb("Expected Param")

        // then
        verify { service.getDataFromDb("Expected Param") }
        assertEquals("Expected Output", result)
    }
}