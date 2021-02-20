package com.josala

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josala.core.model.Crypto
import com.josala.repository.CoinmarketcapRepository
import kotlinx.coroutines.*

class CryptoItemViewModel(
    private val coinmarketcapRepository: CoinmarketcapRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val job = Job()
    private val saveState: MutableLiveData<SaveState> = MutableLiveData()

    fun saveState(): LiveData<SaveState> = saveState

    fun saveCrypto(crypto: Crypto) {
        CoroutineScope(Dispatchers.IO).async {
            val result = coinmarketcapRepository.saveCrypto(crypto)
            withContext(dispatcher) {
                saveState.value = if (result) SaveState.SAVED else SaveState.ERROR
            }
        }
    }

    enum class SaveState {
        SAVED, ERROR
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}