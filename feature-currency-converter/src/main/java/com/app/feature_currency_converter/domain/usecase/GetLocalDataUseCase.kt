package com.app.feature_currency_converter.domain.usecase

import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import com.app.feature_currency_converter.until.Resource
import kotlinx.coroutines.flow.Flow

class GetLocalDataUseCase(private val repositoryCurrencyConverter: RepositoryCurrencyConverter) {
    fun executeLocal(): Flow<Resource<List<CurrencyModelModule>>> {
        return repositoryCurrencyConverter.getLocalCurrency()
    }
}