package com.app.feature_currency_converter.domain.usecase

import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter

class SaveSelectionItemUseCase(private val repositoryCurrencyConverter: RepositoryCurrencyConverter) {
    fun execute(currencyModelModule: CurrencyModelModule) = repositoryCurrencyConverter.saveSelectionItem(currencyModelModule)
}