package com.josala.coinmarketcap

import com.josala.data.remote.coinmarketcap.dao.ListingResponseDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinmarketClient(baseUrl: String): CoinmarketApi {

    private val coinmarketClient = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
        .create(CoinmarketApi::class.java)

    override suspend fun getCryptoListing(): Response<ListingResponseDto>
    = coinmarketClient.getCryptoListing()
}