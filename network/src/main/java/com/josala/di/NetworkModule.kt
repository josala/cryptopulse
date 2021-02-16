package com.josala.di

import com.josala.coinmarketcap.CoinmarketApi
import com.josala.coinmarketcap.CoinmarketClient
import org.koin.dsl.module

const val coinmarketBaseUrl = "https://api.coinmarketcap.com/v2/"

val coinmarketApiModule = module {
    single { CoinmarketClient(coinmarketBaseUrl) as CoinmarketApi }
}