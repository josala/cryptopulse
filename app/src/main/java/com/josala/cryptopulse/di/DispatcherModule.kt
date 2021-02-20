package com.josala.cryptopulse.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named("main")) { Dispatchers.Main as CoroutineDispatcher }
    single(named("io")) { Dispatchers.IO as CoroutineDispatcher }
}