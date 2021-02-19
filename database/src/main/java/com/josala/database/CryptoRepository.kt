package com.josala.database

import com.josala.database.dao.CryptoDao

interface CryptoRepository {

    fun getCryptoDao(): CryptoDao
}

class CryptoRepositoryImpl(private val cryptoDatabase: CryptopulseDatabase) : CryptoRepository {
    override fun getCryptoDao() = cryptoDatabase.cryptoDao()
}