package com.app.database.di

import android.app.Application
import androidx.room.Room
import com.app.database.AppCurrencyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppCurrencyDataBase =
        Room.databaseBuilder(app, AppCurrencyDataBase::class.java, "currency_database")
            .build()

}