package com.josala.domain.model

import com.josala.coinmarketcap.dao.CryptoItemDto

data class CryptoItem(
    val id: Int,
    val name: String,
    val symbol: String
) {
    constructor(cryptoItemDto: CryptoItemDto) : this(
        cryptoItemDto.id,
        cryptoItemDto.name,
        cryptoItemDto.symbol
    )
}