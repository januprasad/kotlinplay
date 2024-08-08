package com.github.interview_prep.dbservice

class DBService {
    fun getDBVersion(): String = "1.0.1"

    fun getSchoolInfo(name: String): School = School(name)
}

data class School(
    val name: String,
)

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        // query database and return matching value
        return testParameter
    }

    fun doSomethingElse(testParameter: String): String = "I don't want to!"
}
