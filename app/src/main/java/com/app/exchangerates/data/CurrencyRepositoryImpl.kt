package com.app.exchangerates.data

import android.util.Log
import androidx.room.withTransaction
import com.app.exchangerates.data.local.AppCurrencyDataBase
import com.app.exchangerates.data.remote.CurrencyApi
import com.app.exchangerates.data.remote.entinity.Valute
import com.app.exchangerates.domain.models.CurrencyModel
import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.until.Resource
import com.app.exchangerates.until.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl(private val api: CurrencyApi, private val appCurrencyDataBase: AppCurrencyDataBase) : RepositoryCurrency {

    private val dao = appCurrencyDataBase.getCurrencyDao()
    private lateinit var response:Valute

    override suspend fun getRemoteCurrency(): Valute {
        val answerApi = api.getCurrencyList()
        answerApi.let {
            response = answerApi.valute
        }
        Log.i("AAAA", "getRemote")
        Log.i("AAAA", "${response.aMD}")
        return response
    }

    override fun getLocalCurrency() = networkBoundResource(
        query = {
            DataProcessing().toCurrencyModel(dao.getAll())
        },
        fetch = {
            delay(2000)
            DataProcessing().toCurrencyDbEntity(mutableListOf(api.getCurrencyList().valute))
        },
        saveFetchResult = { currencyList ->
            appCurrencyDataBase.withTransaction {
                dao.deleteAll()
                dao.insert(currencyList)
            }
        }
    )
}