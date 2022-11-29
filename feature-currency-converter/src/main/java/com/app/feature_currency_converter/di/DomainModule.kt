package com.app.feature_currency_converter.di

import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import com.app.feature_currency_converter.domain.usecase.GetLocalDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSettingsUseCase(repositoryCurrencyConverter: RepositoryCurrencyConverter): GetLocalDataUseCase {
        return GetLocalDataUseCase(repositoryCurrencyConverter)
    }

}