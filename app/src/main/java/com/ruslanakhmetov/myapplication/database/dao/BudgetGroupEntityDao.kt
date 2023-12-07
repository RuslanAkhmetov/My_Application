package com.ruslanakhmetov.myapplication.database.dao

import androidx.room.*
import com.ruslanakhmetov.myapplication.database.BudgetGroupEntity

@Dao
interface BudgetGroupEntityDao {

    @Query("SELECT * FROM budget_group_table")
    suspend fun getAll(): List<BudgetGroupEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BudgetGroupEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<BudgetGroupEntity>)

    @Update
    suspend fun update(entity: BudgetGroupEntity)

    @Delete
    suspend fun delete(entity: BudgetGroupEntity)
}