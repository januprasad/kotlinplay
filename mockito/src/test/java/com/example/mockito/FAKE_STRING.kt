package com.example.mockito

import android.content.Context
import com.example.mockito.utils.ClassUnderTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private const val FAKE_STRING = "2HELLO WORLD"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {
    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        val mockContext =
            mock<Context> {
                on { getString(R.string.name_label) } doReturn FAKE_STRING
            }

        val myObjectUnderTest = ClassUnderTest(mockContext)

        // ...when the string is returned from the object under test...
        val result: String = myObjectUnderTest.getName()

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING)
    }
}
