package com.app.exchangerates.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.exchangerates.domain.usecase.GetDataCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getDataCurrencyUseCase: GetDataCurrencyUseCase) : ViewModel() {
    val currencyLiveData = getDataCurrencyUseCase.executeLocal().asLiveData()

}