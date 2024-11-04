package com.github.kotlin_tryout

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

open class Mobula {
    private val client = HttpClient()

    suspend fun parse(cryptoResponse: String): Deferred<Response> {
        var crypto: Deferred<Response>
        coroutineScope {
            crypto =
                async(Dispatchers.IO) {
                    return@async Json.decodeFromString<Response>(cryptoResponse)
                }
        }
        return crypto
    }

    suspend fun getMethod(param: String): Deferred<String> {
        var response: Deferred<String>
        coroutineScope {
            response =
                async(Dispatchers.IO) {
                    return@async client
                        .get("https://weburl=$param")
                        .bodyAsText()
                }
        }
        return response
    }

    suspend fun test(s: String): Deferred<String> {
        var response: Deferred<String>
        coroutineScope {
            response =
                async(Dispatchers.IO) {
                    return@async s
                }
        }
        return response
    }

    suspend fun getString(s: String): String {
        delay(2000L)
        return s
    }

    fun test1(s: String) = "1"
}
