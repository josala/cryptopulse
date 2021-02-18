package com.josala.core

import com.josala.core.model.Crypto

interface Router {

    companion object {
        const val CRYPTO_ITEM_KEY = "crypto_item"
    }

    fun navigateToCryptoDetail(crypto: Crypto)
}