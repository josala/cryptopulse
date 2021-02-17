package com.josala.coinmarketcap

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinmarketClient(baseUrl: String) : CoinmarketApi {

    private val coinmarketClient = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
        .create(CoinmarketApi::class.java)

    override suspend fun getLatestCryptoListing(
        apiKey: String,
        start: Int,
        limit: Int,
        convert: String
    ) = coinmarketClient.getLatestCryptoListing(apiKey)
}