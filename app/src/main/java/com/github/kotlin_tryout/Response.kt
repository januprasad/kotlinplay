package com.github.kotlin_tryout

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val `data`: Crypto
)