package com.github.januprasad.hilt_example.repo

import com.github.januprasad.hilt_example.common.NetworkResult
import com.github.januprasad.hilt_example.retrofit.QuotesAPI
import kotlinx.coroutines.flow.flow

class QuotesRepositoryImpl(
    val quotesAPI: QuotesAPI,
) : QuoteRepository {
    override suspend fun randomQuote() =
        flow {
            val response = quotesAPI.getQuotes()
            if (response.isSuccess) {
                emit(NetworkResult.Success(data = response.getOrThrow()))
            } else {
                emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
            }
        }
}
