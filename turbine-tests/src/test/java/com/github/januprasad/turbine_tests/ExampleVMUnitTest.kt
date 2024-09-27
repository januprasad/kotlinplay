package com.github.januprasad.turbine_tests

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    lateinit var cryptographicCalculation: CryptographicCalculation

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `Given the sut is initialized, then it waits for event`() {
        val sut = ExampleViewModel(cryptographicCalculation)
        assertTrue(sut.vmState.value == VmState.Waiting)
    }

    @Test
    fun `Given the sut is initialized, then it is should not running`() {
        val sut = ExampleViewModel(cryptographicCalculation)
        assertTrue(sut.vmState.value != VmState.Running)
    }

    @Test
    fun `when request the encryption result string should be valid`() {
        val sut = ExampleViewModel(cryptographicCalculation)
        runTest {
            // ARRANGE
            val expectedString = "Result"
            cryptographicCalculation.stub {
                onBlocking { encryptedString("Hello World") } doAnswer { expectedString }
            }
        }
    }
}
