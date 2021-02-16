package com.josala.coinmarketcap.dao

import com.josala.data.remote.coinmarketcap.dao.MetadataDto

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