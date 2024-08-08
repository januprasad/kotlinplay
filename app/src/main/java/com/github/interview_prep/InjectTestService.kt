package com.github.interview_prep

import com.github.interview_prep.dbservice.TestableService

class InjectTestService {
    lateinit var service1: TestableService
    lateinit var service2: TestableService

    fun invokeService1(): String = service1.getDataFromDb("Test Param")
}
