package com.josala.database.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CRYPTO_TABLE_NAME = "crypto"

@Entity(tableName = CRYPTO_TABLE_NAME)
data class CryptoTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String
)