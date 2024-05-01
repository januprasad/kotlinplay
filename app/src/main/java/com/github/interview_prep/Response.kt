package com.github.interview_prep

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val `data`: Crypto
)