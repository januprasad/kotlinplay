package com.github.interview_prep

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val ath: Double,
    val atl: Double,
    val circulating_supply: String,
    val contracts: List<Contract>,
    val is_listed: Boolean,
    val liquidity: Int,
    val logo: String,
    val market_cap: Long,
    val market_cap_diluted: Long,
    val name: String,
    val off_chain_volume: Long,
    val price: Double,
    val price_change_1h: Double,
    val price_change_1m: Double,
    val price_change_1y: Double,
    val price_change_24h: Double,
    val price_change_7d: Double,
    val rank: Int,
    val symbol: String,
    val total_supply: String,
    val volume: Int,
    val volume_7d: Int,
    val volume_change_24h: Double
)