package com.ruslanakhmetov.myapplication.database.dao

import androidx.room.*
import com.ruslanakhmetov.myapplication.database.BankCardEntity

@Dao
interface BankCardEntityDao {
    @Query("SELECT * FROM bank_card_entity")
    suspend fun getAll(): List<BankCardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BankCardEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<BankCardEntity>)

    @Update
    suspend fun update(entity: BankCardEntity)

    @Delete
    suspend fun delete(entity: BankCardEntity)

    @Query("SELECT COUNT(id) FROM bank_card_entity")
    fun getCount() : Int
}