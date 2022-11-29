package com.app.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.app.database.entity.CurrencyDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencyList: List<CurrencyDbEntity>)

    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<CurrencyDbEntity>>

    @Query("DELETE FROM currency")
    suspend fun deleteAll()

/*
    @Query
    ("SELECT * FROM rockets WHERE id = :id")
    suspend fun getRocketInfo(id: String): List<RocketInfo>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getStageList(id: String): List<RocketWithStage>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getFlickrImageList(id: String): List<RocketWithFlickrImage>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getPayloadWeightList(id: String): List<RocketWithPayloadWeight>
*/

}