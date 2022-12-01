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
        charCodeFirst: String,
        valueFirst: Double = 0.0,
        charCodeSecond: String
    ): Double {
        val firstC = findSelectedItem(currencyModel, charCodeFirst)
        val secondC = findSelectedItem(currencyModel, charCodeSecond)
        val resultBigDecimal = when {
            firstC.value == 0.0 -> {
                println("ПЕРВАЯ ВАЛЮТА ЭТО РУБЛИ")
                (valueFirst / secondC.value).toBigDecimal()
            }
            secondC.value == 0.0 -> {
                println("ВТОРАЯ ВАЛЮТА ЭТО РУБЛИ")
                (valueFirst * firstC.value).toBigDecimal()
            }
            else -> {
                //Считает не правильно
                println("ДВОЙНАЯ КОНВЕРТАЦИЯ")
                (valueFirst * (firstC.value) / secondC.value).toBigDecimal()
            }
        }
        return resultBigDecimal.setScale(4, RoundingMode.HALF_EVEN).toDouble()
    }

    private fun findSelectedItem(currencyModel: List<CurrencyModelApp>, charCode: String): CurrencyModelApp {
        val rub = CurrencyModelApp("RUB", 1, "Российский рубль", 0.0)
        return currencyModel.find { list ->
            charCode == list.charCode
        } ?: rub
    }

}