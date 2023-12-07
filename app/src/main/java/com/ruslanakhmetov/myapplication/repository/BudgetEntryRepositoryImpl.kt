package com.ruslanakhmetov.myapplication.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ruslanakhmetov.myapplication.database.AppDatabase
import com.ruslanakhmetov.myapplication.database.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.database.domain.TransactionSource
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BudgetEntryRepositoryImpl(
    private val db: AppDatabase
) : BudgetEntryRepository {
    private val TAG = "BudgetEntrySMSRepository"

    private var budgetEntries = MutableLiveData<List<BudgetEntry>>()

    override fun getBudgetEntries() {
        GlobalScope.launch {
            budgetEntries.value = db.budgetEntryEntityDao.getAll()?.map {
                BudgetEntry(
                    it.id,
                    it.smsId,
                    it.date,
                    it.operationType,
                    it.operationAmount,
                    TransactionSource.valueOf(it.transactionSource),
                    it.note,
                    it.cardPan
                )
            }
        }
    }


}