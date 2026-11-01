package com.github.januprasad.turbine_tests

interface DataRepository {
    suspend fun fetchData(): String
}

class DefaultDataRepository : DataRepository {
    override suspend fun fetchData(): String {
        return "Blog Turbine Data"
    }
}
