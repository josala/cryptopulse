package com.josala.di

import com.josala.CryptoItemViewModel
import com.josala.CryptoListViewModel
import com.josala.domain.service.CoinmarketService
import com.josala.domain.service.CoinmarketServiceImpl
import com.josala.repository.CoinmarketcapRepository
import com.josala.repository.CoinmarketcapRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coinmarketModule = module {
    viewModel { CryptoListViewModel(get(), get(named("main"))) }
    viewModel { CryptoItemViewModel(get(), get(named("main")), get(named("io"))) }
    single { CoinmarketServiceImpl(get()) as CoinmarketService }
    single { CoinmarketcapRepositoryImpl(get()) as CoinmarketcapRepository }
}