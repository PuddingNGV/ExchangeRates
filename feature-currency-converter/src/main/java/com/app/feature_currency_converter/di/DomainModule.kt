package com.app.feature_currency_converter.di

import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import com.app.feature_currency_converter.domain.usecase.GetLocalDataUseCase
import com.app.feature_currency_converter.domain.usecase.SaveSelectionItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetSettingsUseCase(repositoryCurrencyConverter: RepositoryCurrencyConverter): GetLocalDataUseCase {
        return GetLocalDataUseCase(repositoryCurrencyConverter)
    }

    @Provides
    fun provideSaveSelectionItemUseCase(repositoryCurrencyConverter: RepositoryCurrencyConverter): SaveSelectionItemUseCase {
        return SaveSelectionItemUseCase(repositoryCurrencyConverter)
    }

}