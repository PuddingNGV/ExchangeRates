package com.app.feature_currency_converter.presentation

import androidx.lifecycle.*
import com.app.feature_currency_converter.domain.usecase.GetLocalDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(getLocalDataUseCase: GetLocalDataUseCase) : ViewModel() {
    val currencyConverterTest = getLocalDataUseCase.executeLocal().asLiveData()
}