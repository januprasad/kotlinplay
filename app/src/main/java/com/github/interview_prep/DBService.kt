package com.github.interview_prep


class DBService {
    fun getDBVersion(): String {
        return "1.0.1"
    }

    fun getSchoolInfo(name: String): School {
        return School(name)
    }
}


data class School(val name: String)

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        // query database and return matching value
        return testParameter
    }

    fun doSomethingElse(testParameter: String): String {
        return "I don't want to!"
    }
}