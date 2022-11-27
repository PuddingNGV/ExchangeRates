package com.app.exchangerates.data

import com.app.exchangerates.data.local.entity.CurrencyDbEntity
import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.models.CurrencyModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataProcessing {
    fun toCurrencyModel(listFlow: Flow<List<CurrencyDbEntity>>): Flow<List<CurrencyModel>> {
        val currencyInfoList: Flow<List<CurrencyModel>> = listFlow.map { list ->
            list.map { value ->
                value.toCurrencyModel()
            }
        }
        return currencyInfoList
    }

    fun toCurrencyDbEntity(currencyData: List<CurrencyData>): List<CurrencyDbEntity> {
        val mEntityList = mutableListOf<CurrencyDbEntity>()
        for (i in currencyData.indices) {
            mEntityList.add(currencyData[i].toCurrencyDbEntity())
        }
        return java.util.List.copyOf(mEntityList)
    }
}
