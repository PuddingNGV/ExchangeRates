package com.app.feature_currency_converter.di

import android.content.Context
import com.app.feature_currency_converter.data.RepositoryCurrencyConverterImpl
import com.app.feature_currency_converter.domain.repository.RepositoryCurrencyConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideSettingsRepo(@ApplicationContext context: Context): RepositoryCurrencyConverter {
        return RepositoryCurrencyConverterImpl(context)
    }
}