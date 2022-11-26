package com.app.exchangerates.data.remote.entinity


import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("AMD")
    val aMD: Currency,
    @SerializedName("AUD")
    val aUD: Currency,
    @SerializedName("AZN")
    val aZN: Currency,
    @SerializedName("BGN")
    val bGN: Currency,
    @SerializedName("BRL")
    val bRL: Currency,
    @SerializedName("BYN")
    val bYN: Currency,
    @SerializedName("CAD")
    val cAD: Currency,
    @SerializedName("CHF")
    val cHF: Currency,
    @SerializedName("CNY")
    val cNY: Currency,
    @SerializedName("CZK")
    val cZK: Currency,
    @SerializedName("DKK")
    val dKK: Currency,
    @SerializedName("EUR")
    val eUR: Currency,
    @SerializedName("GBP")
    val gBP: Currency,
    @SerializedName("HKD")
    val hKD: Currency,
    @SerializedName("HUF")
    val hUF: Currency,
    @SerializedName("INR")
    val iNR: Currency,
    @SerializedName("JPY")
    val jPY: Currency,
    @SerializedName("KGS")
    val kGS: Currency,
    @SerializedName("KRW")
    val kRW: Currency,
    @SerializedName("KZT")
    val kZT: Currency,
    @SerializedName("MDL")
    val mDL: Currency,
    @SerializedName("NOK")
    val nOK: Currency,
    @SerializedName("PLN")
    val pLN: Currency,
    @SerializedName("RON")
    val rON: Currency,
    @SerializedName("SEK")
    val sEK: Currency,
    @SerializedName("SGD")
    val sGD: Currency,
    @SerializedName("TJS")
    val tJS: Currency,
    @SerializedName("TMT")
    val tMT: Currency,
    @SerializedName("TRY")
    val tRY: Currency,
    @SerializedName("UAH")
    val uAH: Currency,
    @SerializedName("USD")
    val uSD: Currency,
    @SerializedName("UZS")
    val uZS: Currency,
    @SerializedName("XDR")
    val xDR: Currency,
    @SerializedName("ZAR")
    val zAR: Currency
)