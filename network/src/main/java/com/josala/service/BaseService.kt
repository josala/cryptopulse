package com.josala.service

import retrofit2.Response

abstract class BaseService {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ServiceResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ServiceResult.Success<T>(it)
                }
                return ServiceResult.Error(NetworkError("Null body error"))
            } else {
                val msg = response.errorBody()?.string() ?: "Network error"
                return ServiceResult.Error(NetworkError(msg, response.code()))
            }
        } catch (e: Exception) {
            return ServiceResult.Error(NetworkError(e.localizedMessage))
        }
    }
}