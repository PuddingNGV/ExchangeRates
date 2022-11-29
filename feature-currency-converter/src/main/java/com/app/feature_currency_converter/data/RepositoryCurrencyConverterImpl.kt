package com.app.feature_currency_converter.data

import android.content.Context
import com.app.feature_currency_converter.domain.models.CurrencyModel
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class RepositoryCurrencyConverterImpl(@ApplicationContext private val context: Context) : RepositoryCurrencyConverter {
    override fun getLocalCurrency(): Flow<List<CurrencyModel>> {
        TODO("Not yet implemented")
    }
}