package com.ruslanakhmetov.myapplication.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "sms_data_table")
data class SmsDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val rawSms: String,
    val date: Date
)