package com.josala.di

import com.josala.coinmarketcap.CoinmarketApi
import com.josala.coinmarketcap.CoinmarketClient
import org.koin.dsl.module

const val coinmarketBaseUrl = "https://pro-api.coinmarketcap.com/v1/"

val coinmarketApiModule = module {
    single { CoinmarketClient(coinmarketBaseUrl) as CoinmarketApi }
}