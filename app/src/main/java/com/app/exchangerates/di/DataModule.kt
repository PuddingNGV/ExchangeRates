package com.app.exchangerates.di

import com.app.database.AppCurrencyDataBase
import com.app.exchangerates.data.CurrencyRepositoryImpl
import com.app.exchangerates.data.remote.CurrencyApi
import com.app.exchangerates.domain.repository.RepositoryCurrency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyApi, appCurrencyDataBase: AppCurrencyDataBase): RepositoryCurrency {
        return CurrencyRepositoryImpl(api, appCurrencyDataBase)
    }
}