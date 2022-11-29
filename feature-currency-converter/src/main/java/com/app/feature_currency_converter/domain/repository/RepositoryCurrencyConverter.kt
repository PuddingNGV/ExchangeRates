package com.app.feature_currency_converter.domain.repository

import com.app.feature_currency_converter.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow

interface RepositoryCurrencyConverter {
    fun getLocalCurrency(): Flow<List<CurrencyModel>>
}