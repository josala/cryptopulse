package com.josala

import androidx.lifecycle.ViewModel
import com.josala.domain.service.CoinmarketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoListViewModel(
    private val coinmarketService: CoinmarketService
) : ViewModel() {

    fun getCryptoList() {
        CoroutineScope(Dispatchers.Main).launch {
            coinmarketService.getCryptoItems()
        }
    }
}