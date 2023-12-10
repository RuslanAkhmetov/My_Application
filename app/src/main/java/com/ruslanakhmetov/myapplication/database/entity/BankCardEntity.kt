package com.ruslanakhmetov.myapplication.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_card_entity")
data class BankCardEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bankName: String,
    val cardPan: String,
    var balance: Double
)
