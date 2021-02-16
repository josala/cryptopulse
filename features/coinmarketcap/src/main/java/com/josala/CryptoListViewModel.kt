package com.josala

import androidx.lifecycle.ViewModel
import com.josala.coinmarketcap.CoinmarketApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoListViewModel(
    private val coinmarketApi: CoinmarketApi
): ViewModel() {

    fun getCryptoList() {
        CoroutineScope(Dispatchers.Main).launch {
            coinmarketApi.getCryptoListing()
        }
    }
}