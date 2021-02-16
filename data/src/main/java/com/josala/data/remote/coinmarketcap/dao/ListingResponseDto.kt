package com.josala.data.remote.coinmarketcap.dao

data class CryptoItemDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val website_slug: String
)

data class ListingResponseDto(
    val data: List<CryptoItemDto>,
    val metadata: MetadataDto
)