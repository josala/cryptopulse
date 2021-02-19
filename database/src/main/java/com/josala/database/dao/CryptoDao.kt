package com.josala.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {

    @Query("SELECT * FROM $CRYPTO_TABLE_NAME")
    fun getCrypto(): List<CryptoTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrypto(crypto: CryptoTable)
}