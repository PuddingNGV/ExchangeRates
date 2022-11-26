package com.app.exchangerates.data.local.entity

import androidx.room.*
import com.app.exchangerates.domain.models.CurrencyModel

@Entity (
    tableName = "currency",
    indices = [
        Index("id", unique = true)
    ]
        )
data class CurrencyDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val charCode: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
    ) {
    fun toRocketInfo() : CurrencyModel = CurrencyModel(
        charCode, nominal, name, value
    )
}

