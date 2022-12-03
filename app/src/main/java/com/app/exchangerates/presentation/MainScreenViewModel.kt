package com.app.exchangerates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.exchangerates.domain.models.CurrencyModelApp
import com.app.exchangerates.domain.usecase.GetDataCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import java.math.RoundingMode

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDataCurrencyUseCase: GetDataCurrencyUseCase) : ViewModel() {
    val currencyLiveData = getDataCurrencyUseCase.executeLocal().asLiveData()

    fun convertCurrency(
        currencyModel: List<CurrencyModelApp>,
        charCodeFrom: String,
        input: Double = 0.0,
        charCodeTo: String
    ): Double {
        val currencyFrom = findSelectedItem(currencyModel, charCodeFrom)
        val currencyTo = findSelectedItem(currencyModel, charCodeTo)

        val resultBigDecimal = when {
            currencyFrom.value == 0.0 -> {
                //conversion from rubles
                (input / currencyTo.value * currencyTo.nominal.toDouble()).toBigDecimal()
            }
            currencyTo.value == 0.0 -> {
                //Convert to rubles
                (input * currencyFrom.value / currencyFrom.nominal.toDouble()).toBigDecimal()
            }
            else -> {
                //double currency conversion
                ((((currencyFrom.value / currencyFrom.nominal) * input) / currencyTo.value) *  currencyTo.nominal).toBigDecimal()
            }
        }
        return resultBigDecimal.setScale(2, RoundingMode.HALF_DOWN).toDouble()
    }

    private fun findSelectedItem(currencyModel: List<CurrencyModelApp>, charCode: String): CurrencyModelApp {
        val rub = CurrencyModelApp("RUB", 1, "Российский рубль", 0.0)
        return currencyModel.find { list ->
            charCode == list.charCode
        } ?: rub
    }

}