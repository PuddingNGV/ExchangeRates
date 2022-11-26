package com.app.exchangerates.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.exchangerates.data.local.entity.CurrencyDbEntity

@Database(entities = [CurrencyDbEntity::class], version = 1)
//@TypeConverters(Converter::class)
abstract class AppCurrencyDataBase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}