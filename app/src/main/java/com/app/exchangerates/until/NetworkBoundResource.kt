package com.app.exchangerates.until

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(ResourceApp.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { ResourceApp.Success(it) }
        } catch (throwable: Throwable) {
            query().map { ResourceApp.Error(throwable, it) }
        }
    } else {
        query().map { ResourceApp.Success(it) }
    }

    emitAll(flow)
}