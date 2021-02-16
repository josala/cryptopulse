package com.josala.coinmarketcap

import com.josala.coinmarketcap.dao.ListingResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CoinmarketApi {

    companion object {
        const val LISTING = "listings"
    }

    @GET(LISTING)
    suspend fun getCryptoListing(): Response<ListingResponseDto>
}