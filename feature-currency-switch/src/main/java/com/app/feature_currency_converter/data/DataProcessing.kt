package com.app.feature_currency_converter.data


import com.app.database.entity.CurrencyDbEntity
import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataProcessing {
    fun toCurrencyModel(listFlow: Flow<List<CurrencyDbEntity>>): Flow<List<CurrencyModelModule>> {
        val currencyInfoList: Flow<List<CurrencyModelModule>> = listFlow.map { list ->
            list.map { value ->
                toCurrencyModelConverter(value)
            }
        }
        return currencyInfoList
    }
    private fun toCurrencyModelConverter(currencyDbEntity: CurrencyDbEntity) : CurrencyModelModule = CurrencyModelModule(
        currencyDbEntity.charCode, currencyDbEntity.nominal, currencyDbEntity.name, currencyDbEntity.value
    )
}