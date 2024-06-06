package com.github.interview_prep

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val name: String,
    val price: Double,
    val symbol: String,
)