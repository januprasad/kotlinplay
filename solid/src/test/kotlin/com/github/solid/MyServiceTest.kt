package com.github.solid

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class MyServiceTest{
    @Test
    fun sampleTest(){
        val mockService = mockk<MyService>()
        every { mockService.getData("test") } returns "Mocked Data"
    }
}