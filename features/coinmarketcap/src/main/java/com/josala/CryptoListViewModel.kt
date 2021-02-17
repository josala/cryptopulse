package com.josala

import androidx.lifecycle.ViewModel
import com.josala.domain.service.CoinmarketService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CryptoListViewModel(
    private val coinmarketService: CoinmarketService,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun getCryptoList() {
        CoroutineScope(dispatcher).launch {
            coinmarketService.getCryptoItems()
        }
    }
}