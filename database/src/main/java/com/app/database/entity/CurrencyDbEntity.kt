package com.app.database.entity

import androidx.room.*

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
    )

