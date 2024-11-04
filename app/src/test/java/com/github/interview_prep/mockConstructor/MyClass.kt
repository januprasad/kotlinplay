package com.github.kotlin_tryout.mockConstructor

import io.mockk.every
import io.mockk.mockkConstructor
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MyDependency(
    s: String,
) {
    fun getData(): String = "hello"
}

class MyClass {
    lateinit var dependency: MyDependency

    fun doSomething() {
        dependency = MyDependency("arg")
    }
}

class MyClassTest {
    @Test
    fun `test doSomething`() {
        mockkConstructor(MyDependency::class)
        every { anyConstructed<MyDependency>().getData() } returns "mocked data"
        val myClass = MyClass()
        myClass.doSomething()
        assertEquals("mocked data", myClass.dependency.getData())
    }
}
