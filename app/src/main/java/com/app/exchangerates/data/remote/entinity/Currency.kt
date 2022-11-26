package com.app.exchangerates.data.remote.entinity

import com.app.exchangerates.data.local.entity.CurrencyDbEntity
import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("ID")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("Previous")
    val previous: Double,
    @SerializedName("Value")
    val value: Double
) {
    fun toCurrencyDbEntity(): CurrencyDbEntity = CurrencyDbEntity(
        id, charCode, name, nominal, numCode, previous, value
    )
}