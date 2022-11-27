package com.app.exchangerates.domain.repository

import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.models.CurrencyModel
import com.app.exchangerates.until.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryCurrency {
    suspend fun getRemoteCurrency(): List<CurrencyData>
    fun getLocalCurrency(): Flow<Resource<List<CurrencyModel>>>
}