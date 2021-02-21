package com.josala.favorites.di

import com.josala.favorites.FavoritesViewModel
import com.josala.favorites.repository.FavoriteRepository
import com.josala.favorites.repository.FavoriteRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoritesViewModel(get(), get(named("main")), get(named("io"))) }
    single { FavoriteRepositoryImpl(get()) as FavoriteRepository }
}