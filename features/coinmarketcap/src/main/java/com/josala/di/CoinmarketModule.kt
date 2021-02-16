package com.josala.di

import com.josala.CryptoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinmarketModule = module {
    viewModel { CryptoListViewModel(get()) }
}