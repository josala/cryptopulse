package com.josala.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josala.database.dao.CryptoDao
import com.josala.database.dao.CryptoTable

const val DATABASE_NAME = "cryptopulse.db"
const val DATABASE_VERSION = 1

@Database(entities = arrayOf(CryptoTable::class), version = DATABASE_VERSION)
abstract class CryptopulseDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao
}