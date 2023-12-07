package com.ruslanakhmetov.myapplication.database.dao

import androidx.room.*
import com.ruslanakhmetov.myapplication.database.SmsDataEntity

@Dao
interface SmsDataDao {

    @Query("SELECT * FROM sms_data_table")
    suspend fun getAll(): List<SmsDataEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: SmsDataEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<SmsDataEntity>)

    @Update
    suspend fun update(entity: SmsDataEntity)

    @Delete
    suspend fun delete(entity: SmsDataEntity)
}