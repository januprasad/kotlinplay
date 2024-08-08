package com.github.interview_prep

import com.github.interview_prep.dbservice.TestableService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testCoroutineCancellation() =
        runTest {
            val job =
                launch {
                    while (true) {
                        delay(100)
                    }
                }

            delay(500)
            job.cancel()
            job.join()
        }
}
