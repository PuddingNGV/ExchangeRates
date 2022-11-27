package com.app.exchangerates.domain.usecase

import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.models.CurrencyModel
import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetDataCurrencyUseCase(private val currencyRepository: RepositoryCurrency) {

    suspend fun executeRemote(): List<CurrencyData> {
        return withContext(Dispatchers.IO){
            currencyRepository.getRemoteCurrency()
        }
    }

    fun executeLocal(): Flow<Resource<List<CurrencyModel>>> {
        return currencyRepository.getLocalCurrency()
    }
}