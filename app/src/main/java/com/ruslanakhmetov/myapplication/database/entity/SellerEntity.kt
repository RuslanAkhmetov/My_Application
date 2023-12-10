package com.ruslanakhmetov.myapplication.database.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ruslanakhmetov.myapplication.database.entity.BudgetGroupEntity
import com.ruslanakhmetov.myapplication.domain.TransactionSource

@Entity(
    tableName = "seller_table", foreignKeys = [ForeignKey(
        entity = BudgetGroupEntity::class,
        parentColumns = ["id"],
        childColumns = ["budgetGroupId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class SellerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val transactionSource: TransactionSource
)