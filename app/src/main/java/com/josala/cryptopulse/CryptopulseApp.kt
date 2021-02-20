package com.josala.cryptopulse

import android.app.Application
import com.josala.cryptopulse.di.dispatcherModule
import com.josala.database.di.databaseModule
import com.josala.di.coinmarketApiModule
import com.josala.di.coinmarketModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptopulseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptopulseApp)
            modules(
                listOf(
                    coinmarketApiModule,
                    databaseModule,
                    coinmarketModule,
                    dispatcherModule
                )
            )
        }
    }
}