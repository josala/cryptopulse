package com.josala.coinmarketcap

import com.josala.coinmarketcap.dao.ListingResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CoinmarketApi {

    companion object {
        const val API_KEY = "X-CMC_PRO_API_KEY"
        const val CRYPTOCURRENCY = "cryptocurrency"
        const val LISTINGS = "listings"
        const val MAX_ITEMS = 10
        const val CURRENCY = "USD"
    }

    @GET("$CRYPTOCURRENCY/$LISTINGS/latest")
    suspend fun getLatestCryptoListing(
        @Header(API_KEY) apiKey: String,
        @Header("start") start: Int = 1,
        @Header("limit") limit: Int = MAX_ITEMS,
        @Header("convert") convert: String = CURRENCY
    ): Response<ListingResponseDto>
}