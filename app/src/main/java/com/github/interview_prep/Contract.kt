package com.github.interview_prep

import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val address: String,
    val blockchain: String,
    val blockchainId: String,
    val decimals: Int
)