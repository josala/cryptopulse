package com.josala

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josala.domain.model.CryptoItem
import com.josala.domain.service.CoinmarketService
import com.josala.service.ServiceResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CryptoListViewModel(
    private val coinmarketService: CoinmarketService,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val job = Job()
    private val cryptoList: MutableLiveData<List<CryptoItem>> = MutableLiveData()
    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()

    fun cryptoList(): LiveData<List<CryptoItem>> = cryptoList
    fun loadingState(): LiveData<LoadingState> = loadingState

    fun getCryptoList() {
        loadingState.value = LoadingState.IN_PROGRESS
        CoroutineScope(dispatcher + job).launch {
            when (val listResult = coinmarketService.getCryptoItems()) {
                is ServiceResult.Success -> {
                    loadingState.value = LoadingState.LOADED
                    cryptoList.postValue(listResult.data)
                    println("AAZZ list: ${listResult.data}")
                }
                is ServiceResult.Error -> {
                    loadingState.value = LoadingState.ERROR
                    println("AAZZ error: ${listResult.error.msg}")
                }
            }
        }
    }

    enum class LoadingState {
        IN_PROGRESS, LOADED, ERROR
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}