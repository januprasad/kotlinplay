package com.example.mockito

import io.mockk.every
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class Example2Test {
    private val my = spyk(My())

    @Test
    fun test() {
        val something = "Something"

        every { my.method("something") } returns something
        // now method will return specific value stated above
        assertEquals(something, my.method("something"))

        every { my.method("something") } answers { callOriginal() }
        // now method will call original code
        assertEquals("My something is FUN!", my.method("something"))
    }
}

class My {
    fun method(item: String): String = "My $item is FUN!"
}
