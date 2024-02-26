package com.fjr619.weatherkmm.domain.model

sealed class Response<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : Response<T>()
    data class Error(val throwable: Throwable?) : Response<Nothing>()
}