package com.example.mockito.network

import com.example.mockito.networkservice.DataFetcher
import com.example.mockito.networkservice.NetworkService
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DataFetcherTest {
    @Test
    fun `should fetch and process data`() =
        runBlocking {
            val networkService = mockk<NetworkService>()
            val dataFetcher = DataFetcher(networkService)

            val expectedData = "some data"
            coEvery { networkService.fetchData() } returns expectedData

            val result = dataFetcher.fetchAndProcessData()

            coVerify(exactly = 1) { networkService.fetchData() }

            assertEquals(expectedData.uppercase(), result)
        }
}
