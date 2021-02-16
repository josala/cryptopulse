package com.josala.data.remote.coinmarketcap

import com.josala.data.remote.coinmarketcap.dao.ListingResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface CoinmarketApi {

    companion object {
        const val LISTING = "listings"
    }

    @GET(LISTING)
    suspend fun getCryptoListing(): Response<ListingResponseDto>
}