package com.josala.domain.model

import com.josala.coinmarketcap.dao.CryptoItemDto
import kotlin.math.roundToInt

data class CryptoItem(
    val id: Int,
    val name: String,
    val symbol: String,
    val supply: String,
    val price: String
) {
    constructor(cryptoItemDto: CryptoItemDto) : this(
        id = cryptoItemDto.id,
        name = cryptoItemDto.name,
        symbol = cryptoItemDto.symbol,
        supply = cryptoItemDto.max_supply?.toString() ?: "Infinite",
        price = cryptoItemDto.quote.USD.price.roundToInt().toString()
    )
}