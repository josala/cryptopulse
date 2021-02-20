package com.josala.repository

import com.josala.core.model.Crypto
import com.josala.database.CryptoRepository
import com.josala.database.dao.CryptoTable

interface CoinmarketcapRepository {

    suspend fun saveCrypto(cryptoItem: Crypto): Boolean
}

class CoinmarketcapRepositoryImpl(private val cryptoRepository: CryptoRepository) :
    CoinmarketcapRepository {

    override suspend fun saveCrypto(cryptoItem: Crypto): Boolean {
        with(cryptoItem) {
            val cryptoDao = cryptoRepository.getCryptoDao()
            val cryptoTableEntry = CryptoTable(id, name, price)
            cryptoDao.insertCrypto(cryptoTableEntry)
            return true
        }
    }
}