package com.josala.features.coinmarketcap

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josala.CryptoListViewModel
import com.josala.domain.model.CryptoItem
import com.josala.domain.service.CoinmarketService
import com.josala.service.NetworkError
import com.josala.service.ServiceResult
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CryptoListViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockCoinmarketService: CoinmarketService
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var cryptoListViewModel: CryptoListViewModel

    @Before
    fun setUp() {
        mockCoinmarketService = Mockito.mock(CoinmarketService::class.java)
        cryptoListViewModel = CryptoListViewModel(mockCoinmarketService, testDispatcher)
    }

    @Test
    fun statusCryptoList() = testDispatcher.runBlockingTest {
        // Given
        val cryptoA = CryptoItem(1, "bitcoin", "btc", "21M", "50K")
        val cryptoB = CryptoItem(2, "ethereum", "eth", "Infinite", "2K")
        val cryptoList = listOf(cryptoA, cryptoB)
        `when`(mockCoinmarketService.getCryptoItems()).thenReturn(ServiceResult.Success(cryptoList))
        // When
        cryptoListViewModel.getCryptoList()
        // Then
        assertThat(cryptoListViewModel.cryptoList().value, CoreMatchers.`is`(cryptoList))
        assertThat(
            cryptoListViewModel.loadingState().value,
            CoreMatchers.`is`(CryptoListViewModel.LoadingState.LOADED)
        )
    }

    @Test
    fun statusCryptoListError() = testDispatcher.runBlockingTest {
        // Given
        val networkError = NetworkError("Service unavailable", 503)
        `when`(mockCoinmarketService.getCryptoItems()).thenReturn(ServiceResult.Error(networkError))
        // When
        cryptoListViewModel.getCryptoList()
        // Then
        assertThat(
            cryptoListViewModel.loadingState().value,
            CoreMatchers.`is`(CryptoListViewModel.LoadingState.ERROR)
        )
    }
}