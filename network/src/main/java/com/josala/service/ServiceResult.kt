package com.josala.service

sealed class ServiceResult<out T> {
    data class Success<out T >(val data: T) : ServiceResult<T>()
    data class Error(val error: NetworkError) : ServiceResult<Nothing>()
}

data class NetworkError(val msg: String? = null, val code: Int? = null)