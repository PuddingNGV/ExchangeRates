package com.app.feature_currency_converter.domain.usecase

import com.app.feature_currency_converter.domain.models.CurrencyModel
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import kotlinx.coroutines.flow.Flow

class GetLocalDataUseCase(private val repositoryCurrencyConverter: RepositoryCurrencyConverter) {
    fun executeLocal(): Flow<List<CurrencyModel>> {
        return repositoryCurrencyConverter.getLocalCurrency()
    }
}