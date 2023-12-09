package com.ruslanakhmetov.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.ruslanakhmetov.myapplication.database.AppDatabase
import com.ruslanakhmetov.myapplication.database.BudgetEntryEntity
import com.ruslanakhmetov.myapplication.database.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.database.domain.TransactionSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BudgetEntryRepositoryImpl(
    private val db: AppDatabase
) : BudgetEntryRepository {
    private val TAG = "BudgetEntrySMSRepository"
    private val budgetEntityDAO = db.budgetEntryEntityDao
    private var budgetEntriesLiveData = MutableLiveData<AppState>()

    init {
        loadEntutiesFromDB()

    }

    fun loadEntutiesFromDB() {
        GlobalScope.launch {
            budgetEntriesLiveData.value = AppState.Loading
            try {
                budgetEntityDAO.getAll()?.let {
                    budgetEntriesLiveData.value = AppState.Success(it.map {
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
                    })
                }
            } catch (e: Throwable) {
                budgetEntriesLiveData.value = AppState.Error(e)
            }
        }
    }

    fun loadBudgetEntriesToDB(budgetEntries: List<BudgetEntry>) {     //Обновится или нет
        GlobalScope.launch {
            for (entry in budgetEntries) {
                val budgetEntryEntity = BudgetEntryEntity(
                    entry.id,
                    entry.smsId,
                    entry.date,
                    entry.operationType,
                    entry.operationAmount,
                    entry.transactionSource.name,
                    entry.note,
                    entry.cardPan
                )
                budgetEntityDAO.insert(budgetEntryEntity)
            }

        }
    }


    override fun getBudgetEntries(): MutableLiveData<AppState> = budgetEntriesLiveData


}