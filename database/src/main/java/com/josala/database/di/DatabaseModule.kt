package com.josala.database.di

import androidx.room.Room
import com.josala.database.CryptoRepository
import com.josala.database.CryptoRepositoryImpl
import com.josala.database.CryptopulseDatabase
import com.josala.database.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            CryptopulseDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { CryptoRepositoryImpl(get()) as CryptoRepository }
}