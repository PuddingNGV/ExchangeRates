package com.app.feature_currency_converter.data

import android.content.Context
import com.app.database.AppCurrencyDataBase
import com.app.feature_currency_converter.domain.models.CurrencyModelModule
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import com.app.feature_currency_converter.until.networkBoundResource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Singleton


private const val SHARED_PREFS_SETTINGS = "shared_prefs_settings"
private const val KEY_CHAR_CODE = "char_code_settings"
private const val KEY_NOMINAL = "nominal_settings"
private const val KEY_NAME = "name_settings"
private const val KEY_VALUE = "value_settings"

@Singleton
class RepositoryCurrencyConverterImpl(
    @ApplicationContext private val context: Context,
    private val appCurrencyDataBase: AppCurrencyDataBase
) : RepositoryCurrencyConverter {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_SETTINGS, Context.MODE_PRIVATE)
    private val dao = appCurrencyDataBase.getCurrencyDao()

    override fun getLocalCurrency() = networkBoundResource(
        query = {
            DataProcessing().toCurrencyModel(dao.getAll())
        },
        fetch = {
            delay(10)
        },
        saveFetchResult = {
        }
    )

    override fun saveSelectionItem(currencyModelModule: CurrencyModelModule) {
        sharedPreferences.edit().putString(KEY_CHAR_CODE, currencyModelModule.charCode).apply()
        sharedPreferences.edit().putInt(KEY_NOMINAL, currencyModelModule.nominal).apply()
        sharedPreferences.edit().putString(KEY_NAME, currencyModelModule.name).apply()
        sharedPreferences.edit().putLong(KEY_VALUE, currencyModelModule.value.toRawBits()).apply()
    }
}