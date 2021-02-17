package com.josala.di

import com.josala.CryptoListViewModel
import com.josala.domain.service.CoinmarketService
import com.josala.domain.service.CoinmarketServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinmarketModule = module {
    viewModel { CryptoListViewModel(get(), get()) }
    single { CoinmarketServiceImpl(get()) as CoinmarketService }
}