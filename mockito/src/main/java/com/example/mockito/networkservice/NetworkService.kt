package com.example.mockito.networkservice

interface NetworkService {
    suspend fun fetchData(): String
}

class DataFetcher(
    private val networkService: NetworkService,
) {
    suspend fun fetchAndProcessData(): String {
        val data = networkService.fetchData()
        // Process data
        return data.uppercase()
    }
}

class NetworkServiceImpl : NetworkService {
    override suspend fun fetchData(): String = "hello"
}
