package com.github.januprasad.hilt_example.repo

import com.github.januprasad.hilt_example.common.NetworkResult
import com.github.januprasad.hilt_example.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun randomQuote(): Flow<NetworkResult<Quote>>
}
