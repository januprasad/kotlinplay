package com.github.interview_prep

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Mobula {
    private val client = HttpClient()

    suspend fun parse(cryptoResponse: String): Crypto {
        var crypto: Deferred<Response>
        coroutineScope {
            crypto = async(Dispatchers.IO) {
                return@async Json.decodeFromString<Response>(cryptoResponse)
            }
        }
        return crypto.await().data
    }

    suspend fun getPrice(symbol: String = "BTC"): String {
        var response: Deferred<String>
        coroutineScope {
            response = async(Dispatchers.IO) {
                return@async client.get("https://api.mobula.io/api/1/market/data?symbol=$symbol")
                    .bodyAsText()
            }
        }
        return response.await()
    }
}