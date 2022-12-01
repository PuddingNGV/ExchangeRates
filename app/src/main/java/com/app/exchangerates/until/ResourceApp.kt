package com.app.exchangerates.until

sealed class ResourceApp<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : ResourceApp<T>(data)
    class Loading<T>(data: T? = null) : ResourceApp<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : ResourceApp<T>(data, throwable)
}