package com.josala.coinmarketcap.dao

data class CryptoItemDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val max_supply: Long?,
    val quote: QuoteDto
)

data class QuoteDto(
    val USD: UsdPriceDto
)

data class UsdPriceDto(
    val price: Float,
    val percent_change_24h: Float,
    val market_cap: Float
)

data class ListingResponseDto(
    val data: List<CryptoItemDto>,
    val status: StatusDto
)