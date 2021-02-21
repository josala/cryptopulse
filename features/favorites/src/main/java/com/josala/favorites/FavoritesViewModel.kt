package com.josala.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josala.core.model.Crypto
import com.josala.favorites.repository.FavoriteRepository
import kotlinx.coroutines.*

class FavoritesViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherIo: CoroutineDispatcher
) : ViewModel() {

    private val job = Job()
    private val cryptoList: MutableLiveData<List<Crypto>> = MutableLiveData()

    fun cryptoList(): LiveData<List<Crypto>> = cryptoList

    fun getSavedCryptos() {
        CoroutineScope(dispatcherIo + job).launch {
            val savedCryptos = favoriteRepository.getCryptos()
            withContext(dispatcher) {
                cryptoList.value = savedCryptos
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}