package com.github.kotlin_tryout

import com.github.kotlin_tryout.dbservice.TestableService

class InjectTestService {
    lateinit var service1: TestableService
    lateinit var service2: TestableService

    fun invokeService1(): String = service1.getDataFromDb("Test Param")
}
