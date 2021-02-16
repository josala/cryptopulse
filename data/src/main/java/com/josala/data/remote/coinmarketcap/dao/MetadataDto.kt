package com.josala.data.remote.coinmarketcap.dao

data class MetadataDto(
    val timestamp: Long,
    val num_cryptocurrencies: Int,
    val error: String?
)