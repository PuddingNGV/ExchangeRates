package com.app.exchangerates.data.remote

import com.app.exchangerates.data.remote.entinity.Entinity
import com.app.exchangerates.data.remote.entinity.Valute
import retrofit2.http.GET

interface CurrencyApi {
    @GET("daily_json.js")
    suspend fun getCurrencyList(): Entinity

}