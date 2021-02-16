package com.josala.cryptopulse

import android.app.Application
import com.josala.di.coinmarketApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptopulseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptopulseApp)
            modules(listOf(
                coinmarketApiModule
            ))
        }
    }
}