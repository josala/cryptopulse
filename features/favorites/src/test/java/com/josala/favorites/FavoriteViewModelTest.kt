package com.josala.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josala.core.model.Crypto
import com.josala.favorites.repository.FavoriteRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class FavoriteViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mockFavoriteRepository: FavoriteRepository
    lateinit var favoritesViewModel: FavoritesViewModel

    @Before
    fun setUp() {
        mockFavoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        favoritesViewModel = FavoritesViewModel(mockFavoriteRepository, testDispatcher, testDispatcher)
    }

    @Test
    fun getSavedCryptos() = testDispatcher.runBlockingTest {
        // Given
        val cryptos = listOf(Crypto(1, "bitcoin", "50K"))
        Mockito.`when`(mockFavoriteRepository.getCryptos()).thenReturn(cryptos)
        // When
        favoritesViewModel.getSavedCryptos()
        // Then
        Assertions.assertThat(favoritesViewModel.cryptoList().value).isEqualTo(cryptos)
    }
}