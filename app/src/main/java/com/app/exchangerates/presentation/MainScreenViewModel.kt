package com.app.exchangerates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.exchangerates.domain.usecase.GetDataCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDataCurrencyUseCase: GetDataCurrencyUseCase) : ViewModel() {
    val currencyLiveData = getDataCurrencyUseCase.executeLocal().asLiveData()

}