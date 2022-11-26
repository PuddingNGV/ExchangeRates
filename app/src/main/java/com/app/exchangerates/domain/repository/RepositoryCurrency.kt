package com.app.exchangerates.domain.repository

import com.app.exchangerates.data.remote.entinity.Valute
import com.app.exchangerates.domain.models.CurrencyModel
import com.app.exchangerates.until.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryCurrency {
    suspend fun getRemoteCurrency(): Valute
    fun getLocalCurrency(): Flow<Resource<List<CurrencyModel>>>
}