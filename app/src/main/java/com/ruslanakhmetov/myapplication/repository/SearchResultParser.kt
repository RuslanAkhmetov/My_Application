package com.ruslanakhmetov.myapplication.repository

import com.ruslanakhmetov.myapplication.database.entity.BudgetEntryEntity
import com.ruslanakhmetov.myapplication.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.domain.TransactionSource

fun budgetEntryEntityListToBudgetEntryList(budgetEntryEntities: List<BudgetEntryEntity>) : List<BudgetEntry>? =
    budgetEntryEntities?.map { entity ->
        BudgetEntry(entity.id,
            entity.smsId,
            entity.date,
            entity.operationType,
            entity.operationAmount,
            TransactionSource.valueOf(entity.transactionSource),
            entity.note,
            entity.cardPan
        )
    }