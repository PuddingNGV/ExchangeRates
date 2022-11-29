package com.app.feature_currency_converter.data

import android.content.Context
import com.app.feature_currency_converter.domain.models.CurrencyModel
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


private const val SHARED_PREFS_SETTINGS = "shared_prefs_settings"
private const val KEY_CHAR_CODE = "char_code_settings"
private const val KEY_NOMINAL = "nominal_settings"
private const val KEY_NAME = "name_settings"
private const val KEY_VALUE = "value_settings"

@Singleton
class RepositoryCurrencyConverterImpl(@ApplicationContext private val context: Context) : RepositoryCurrencyConverter {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE)

    override fun getLocalCurrency(): Flow<List<CurrencyModel>> {
        TODO("Not yet implemented")
    }

    fun putSharedPreferencesData(currencyModel: CurrencyModel) {
        sharedPreferences.edit().putString(KEY_CHAR_CODE, currencyModel.charCode).apply()
        sharedPreferences.edit().putInt(KEY_NOMINAL, currencyModel.nominal).apply()
        sharedPreferences.edit().putString(KEY_NAME, currencyModel.name).apply()
        sharedPreferences.edit().putLong(KEY_VALUE, currencyModel.value.toRawBits()).apply()
    }
}