package com.github.interview_prep

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

class Mobula {
    private val client = HttpClient()

    suspend fun parse(cryptoResponse: String): Deferred<Response> {
        var crypto: Deferred<Response>
        coroutineScope {
            crypto = async(Dispatchers.IO) {
                return@async Json.decodeFromString<Response>(cryptoResponse)
            }
        }
        return crypto
    }

    suspend fun getPrice(symbol: String = "BTC", dispatcherProvider: DispatcherProvider): Deferred<String> {
        var response: Deferred<String>
        coroutineScope {
            response = async(dispatcherProvider.io) {
                return@async client.get("https://api.mobula.io/api/1/market/data?symbol=$symbol")
                    .bodyAsText()
            }
        }
        return response
    }

    suspend fun test(s: String): Deferred<String> {
        var response: Deferred<String>
        coroutineScope {
            response = async(Dispatchers.IO) {
                return@async "Abc"
            }
        }
        return response
    }

    fun test1(s: String) = "1"
}