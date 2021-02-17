package com.josala.features.coinmarketcap

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.josala.coinmarketcap.CoinmarketApi
import com.josala.coinmarketcap.dao.*
import com.josala.domain.model.CryptoItem
import com.josala.domain.service.CoinmarketService
import com.josala.domain.service.CoinmarketServiceImpl
import com.josala.service.NetworkError
import com.josala.service.ServiceResult
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response


class CoinmarketServiceTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockCoinmarketApi: CoinmarketApi
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var coinmarketService: CoinmarketService

    @Before
    fun setUp() {
        mockCoinmarketApi = mock(CoinmarketApi::class.java)
        coinmarketService = CoinmarketServiceImpl(mockCoinmarketApi)
    }

    @Test
    fun serviceGetListSuccess() = testDispatcher.runBlockingTest {
        //Given
        val cryptoA = CryptoItemDto(
            1, "bitcoin", "btc", 21000000L, QuoteDto(
                UsdPriceDto(
                    50.0F,
                    10.0F,
                    18.7F
                )
            )
        )
        val statusDto = StatusDto(200, null)
        val listingResponseDto = ListingResponseDto(listOf(cryptoA), statusDto)
        val apiResponse = Response.success(listingResponseDto)
        //When
        `when`(mockCoinmarketApi.getLatestCryptoListing()).thenReturn(apiResponse)
        //Then
        val expectedResult: ServiceResult<List<CryptoItem>> = ServiceResult.Success(
            listOf(CryptoItem(cryptoA))
        )
        assertThat(coinmarketService.getCryptoItems()).isEqualTo(expectedResult)
    }

    @Test
    fun serviceGetListError() = testDispatcher.runBlockingTest {
        //Given
        val errorCode = 404
        val json = "{ \"message\" : \"Not Found\" }"
        //When
        `when`(mockCoinmarketApi.getLatestCryptoListing()).thenReturn(
            Response.error(
                errorCode,
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    json
                )
            )
        )
        //Then
        val networkError = NetworkError(json, errorCode)
        assertThat(coinmarketService.getCryptoItems()).isEqualTo(ServiceResult.Error(networkError))
    }
}