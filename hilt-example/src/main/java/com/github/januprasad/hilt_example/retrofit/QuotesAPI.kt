package com.github.januprasad.hilt_example.retrofit

import com.github.januprasad.hilt_example.model.Quote
import retrofit2.http.GET

interface QuotesAPI {
    @GET("/quotes/random")
    suspend fun getQuotes(): Result<Quote>
}
