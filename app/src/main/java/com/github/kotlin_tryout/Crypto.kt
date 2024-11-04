package com.github.kotlin_tryout

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val name: String,
    val price: Double,
    val symbol: String,
)