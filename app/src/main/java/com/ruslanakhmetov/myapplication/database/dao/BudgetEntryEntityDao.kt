package com.ruslanakhmetov.myapplication.database.dao

import androidx.room.*
import com.ruslanakhmetov.myapplication.database.entity.BudgetEntryEntity
import com.ruslanakhmetov.myapplication.domain.OperationType


@Dao
interface BudgetEntryEntityDao {

    @Query("SELECT * FROM budget_entry_table")
    suspend fun getAll(): List<BudgetEntryEntity>

    @Query("SELECT * FROM budget_entry_table WHERE operationType = :operationType")
    suspend fun getByOperationType(operationType: OperationType): List<BudgetEntryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BudgetEntryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<BudgetEntryEntity>)

    @Update
    suspend fun update(entity: BudgetEntryEntity)

    @Delete
    suspend fun delete(entity: BudgetEntryEntity)

    @Query("SELECT COUNT(id) FROM budget_entry_table")
    suspend fun getCount() : Int
}