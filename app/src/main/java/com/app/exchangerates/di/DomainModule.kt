package com.app.exchangerates.di

import com.app.exchangerates.domain.repository.RepositoryCurrency
import com.app.exchangerates.domain.usecase.GetDataCurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetDataUseCase(repositoryCurrency: RepositoryCurrency): GetDataCurrencyUseCase {
        return GetDataCurrencyUseCase(repositoryCurrency)
    }
}