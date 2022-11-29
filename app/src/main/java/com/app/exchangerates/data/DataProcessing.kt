package com.app.exchangerates.data

import com.app.database.entity.CurrencyDbEntity
import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.models.CurrencyModelApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataProcessing {
    fun toCurrencyModel(listFlow: Flow<List<CurrencyDbEntity>>): Flow<List<CurrencyModelApp>> {
        val currencyInfoList: Flow<List<CurrencyModelApp>> = listFlow.map { list ->
            list.map { value ->
                toCurrencyModelConverter(value)
            }
        }
        return currencyInfoList
    }

    private fun toCurrencyModelConverter(currencyDbEntity: CurrencyDbEntity) : CurrencyModelApp = CurrencyModelApp(
        currencyDbEntity.charCode, currencyDbEntity.nominal, currencyDbEntity.name, currencyDbEntity.value
    )

    fun toCurrencyDbEntity(currencyData: List<CurrencyData>): List<CurrencyDbEntity> {
        val mEntityList = mutableListOf<CurrencyDbEntity>()
        for (i in currencyData.indices) {
            mEntityList.add(currencyData[i].toCurrencyDbEntity())
        }
        return java.util.List.copyOf(mEntityList)
    }
}
