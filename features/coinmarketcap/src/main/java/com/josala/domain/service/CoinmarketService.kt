package com.josala.domain.service

import com.josala.coinmarketcap.CoinmarketApi
import com.josala.domain.model.CryptoItem
import com.josala.service.BaseService
import com.josala.service.ServiceResult

interface CoinmarketService {

    suspend fun getCryptoItems(): ServiceResult<List<CryptoItem>>
}

class CoinmarketServiceImpl(private val coinmarketApi: CoinmarketApi) : BaseService(),
    CoinmarketService {

    override suspend fun getCryptoItems(): ServiceResult<List<CryptoItem>> {
        return when (val result = getResult { coinmarketApi.getLatestCryptoListing() }) {
            is ServiceResult.Success -> {
                val cryptoList = result.data.data.map { CryptoItem(it) }
                ServiceResult.Success(cryptoList)
            }
            is ServiceResult.Error -> result
        }
    }
}