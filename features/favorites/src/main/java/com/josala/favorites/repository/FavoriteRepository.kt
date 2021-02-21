package com.josala.favorites.repository

import com.josala.core.model.Crypto
import com.josala.database.CryptoRepository

interface FavoriteRepository {

    suspend fun getCryptos(): List<Crypto>
}

class FavoriteRepositoryImpl(private val cryptoRepository: CryptoRepository) : FavoriteRepository {

    override suspend fun getCryptos(): List<Crypto> {
        val result = ArrayList<Crypto>()
        val cryptoDao = cryptoRepository.getCryptoDao()
        cryptoDao.getCrypto().forEach {
            with(it) {
                result.add(Crypto(id, name, price))
            }
        }
        return result
    }
}