package com.app.exchangerates.domain.repository

import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.models.CurrencyModelApp
import com.app.exchangerates.until.ResourceApp
import kotlinx.coroutines.flow.Flow

interface RepositoryCurrency {
    suspend fun getRemoteCurrency(): List<CurrencyData>
    fun getLocalCurrency(): Flow<ResourceApp<List<CurrencyModelApp>>>
}