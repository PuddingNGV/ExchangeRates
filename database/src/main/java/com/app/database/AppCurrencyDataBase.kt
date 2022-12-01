package com.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.database.dao.CurrencyDao
import com.app.database.entity.CurrencyDbEntity


@Database(entities = [CurrencyDbEntity::class], version = 1)
//@TypeConverters(Converter::class)
abstract class AppCurrencyDataBase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}
