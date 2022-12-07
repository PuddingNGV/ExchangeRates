package com.app.exchangerates.data

import android.util.Log
import androidx.room.withTransaction
import com.app.database.AppCurrencyDataBase
import com.app.exchangerates.data.remote.CurrencyApi
import com.app.exchangerates.data.remote.entinity.CurrencyData
import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.until.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl(
    private val api: CurrencyApi,
    private val appCurrencyDataBase: AppCurrencyDataBase
) : RepositoryCurrency {

    private val dao = appCurrencyDataBase.getCurrencyDao()
    private lateinit var response: List<CurrencyData>

    override suspend fun getRemoteCurrency(): List<CurrencyData> {
        val answerApi = api.getCurrencyList()
        answerApi.let {
            response = answerApi.valute.values.toList()
        }
        //Log.i("AAAA", "${response}")
        return response
    }

    override fun getLocalCurrency() = networkBoundResource(
        query = {
            DataProcessing().toCurrencyModel(dao.getAll())
        },
        fetch = {
            delay(2000)
            DataProcessing().toCurrencyDbEntity(getRemoteCurrency())
        },
        saveFetchResult = { currencyList ->
            Log.i("Services", currencyList[0].toString())
            appCurrencyDataBase.withTransaction {
                dao.deleteAll()
                dao.insert(currencyList)
            }
        }
    )
}