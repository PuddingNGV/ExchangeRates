package com.app.feature_currency_converter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import com.app.feature_currency_converter.domain.usecase.GetLocalDataUseCase
import com.app.feature_currency_converter.domain.usecase.SaveSelectionItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val getLocalDataUseCase: GetLocalDataUseCase,
    private val saveSelectionItemUseCase: SaveSelectionItemUseCase
) : ViewModel() {
    val currencyConverterLiveData = getLocalDataUseCase.executeLocal().asLiveData()

    fun selectedItem(currencyModelModule: CurrencyModelModule) = saveSelectionItemUseCase.execute(currencyModelModule)

}