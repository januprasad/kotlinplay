package com.github.interview_prep

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AnnotationMockKUnitTest {

//    @MockK
//    lateinit var service1: TestableService

    @RelaxedMockK
    lateinit var service: TestableService

    @InjectMockKs
    var objectUnderTest = InjectTestService()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun givenServiceSpy_whenMockingOnlyOneMethod_thenOtherMethodsShouldBehaveAsOriginalObject() {
        // given
        val service = spyk<TestableService>()
        every { service.getDataFromDb(any()) } returns "Mocked Output"

        // when checking mocked method
        val firstResult = service.getDataFromDb("Any Param")

        // then
        assertEquals("Mocked Output", firstResult)

        // when checking not mocked method
        val secondResult = service.doSomethingElse("Any Param")

        // then
        assertEquals("I don't want to!", secondResult)
    }

    @Test
    fun givenServiceSpy_2() {
        // given
        every { service.doSomethingElse(any()) } returns "Mocked Output"

        // when checking mocked method
        val firstResult = service.doSomethingElse("Any Param")

        // then
        assertEquals("Mocked Output", firstResult)

        // when checking not mocked method
//        val secondResult = service.getDataFromDb("Any Param")

        // then
//        assertEquals("Any Param", secondResult)
    }
    @Test
    fun givenRelaxedMock_whenCallingNotMockedMethod_thenReturnDefaultValue() {
        // given
        val service = mockk<TestableService>(relaxed = true)

        // when
        val result = service.getDataFromDb("Any Param")

        // then
        assertEquals("", result)
    }
}
