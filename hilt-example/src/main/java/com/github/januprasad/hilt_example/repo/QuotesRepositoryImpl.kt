package com.github.januprasad.hilt_example.repo

import com.github.januprasad.hilt_example.common.NetworkResult
import com.github.januprasad.hilt_example.model.Quote
import com.github.januprasad.hilt_example.retrofit.QuotesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuotesRepositoryImpl(
    val quotesAPI: QuotesAPI,
) : QuoteRepository {
    override fun randomQuote() =
        flow<NetworkResult<Quote>> {
            emit(NetworkResult.Loading())
            val response = quotesAPI.getQuotes()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(response.body()))
                } ?: run {
                    emit(NetworkResult.Error(message = "Error occurred"))
                }
            }
        }.flowOn(Dispatchers.IO)
}
