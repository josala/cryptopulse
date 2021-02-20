package com.josala.features.coinmarketcap

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josala.CryptoItemViewModel
import com.josala.core.model.Crypto
import com.josala.repository.CoinmarketcapRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CryptoItemViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mockCoinmarketcapRepository: CoinmarketcapRepository
    lateinit var cryptoItemViewModel: CryptoItemViewModel

    @Before
    fun setUp() {
        mockCoinmarketcapRepository = Mockito.mock(CoinmarketcapRepository::class.java)
        cryptoItemViewModel = CryptoItemViewModel(mockCoinmarketcapRepository, testDispatcher)
    }

    @Test
    fun saveCryptoItem() = testDispatcher.runBlockingTest {
        // Given
        val cryptoItem = Crypto(1, "bitcoin", "50K")
        `when`(mockCoinmarketcapRepository.saveCrypto(cryptoItem)).thenReturn(true)
        // When
        cryptoItemViewModel.saveCrypto(cryptoItem)
        // Then
        assertThat(cryptoItemViewModel.saveState().value).isEqualTo(CryptoItemViewModel.SaveState.SAVED)
    }

    @Test
    fun saveCryptoItemError() = testDispatcher.runBlockingTest {
        // Given
        val cryptoItem = Crypto(1, "bitcoin", "50K")
        `when`(mockCoinmarketcapRepository.saveCrypto(cryptoItem)).thenReturn(false)
        // When
        cryptoItemViewModel.saveCrypto(cryptoItem)
        // Then
        assertThat(cryptoItemViewModel.saveState().value).isEqualTo(CryptoItemViewModel.SaveState.ERROR)
    }
}