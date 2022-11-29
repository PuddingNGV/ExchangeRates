package com.app.feature_currency_converter.domain.repository

import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import com.app.feature_currency_converter.until.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryCurrencyConverter {
    fun getLocalCurrency(): Flow<Resource<List<CurrencyModelModule>>>
}