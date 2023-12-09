package com.ruslanakhmetov.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslanakhmetov.myapplication.database.AppDatabase
import com.ruslanakhmetov.myapplication.database.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.database.domain.SMS
import com.ruslanakhmetov.myapplication.repository.AppState
import com.ruslanakhmetov.myapplication.repository.BudgetEntryRepository
import com.ruslanakhmetov.myapplication.repository.BudgetEntryRepositoryImpl
import com.ruslanakhmetov.myapplication.repository.BudgetEntrySMSRepository
import javax.inject.Inject

class MainActivityViewModel() : ViewModel() {
    @Inject
    lateinit var db: AppDatabase
    private val TAG = "MainActivityViewModel"
    private var liveDataToObserve: MutableLiveData<List<BudgetEntry>>? = MutableLiveData()
    private val repositoryDB = BudgetEntryRepositoryImpl(db)
    private val repositorySMS = BudgetEntrySMSRepository(App.app)

    fun getBudgetEntries(): MutableLiveData<AppState>? {
        var budgetEntries: MutableLiveData<AppState> = MutableLiveData(AppState.Loading)
        budgetEntries = repositoryDB.getBudgetEntries()
        if (budgetEntries.value is AppState.Success && (budgetEntries.value as AppState.Success).budgetEntries.isEmpty()) {
            Log.i(TAG, "getBudgetEntries: DB is empty")
            initializeDataBase()
        }

        return budgetEntries
    }

    private fun initializeDataBase() {
        val smsList = repositorySMS.getSMSList()

        if (smsList != null && smsList.value != null) {
            if (db.bankCardEntityDao.getCount() == 0) {
                initializeBankCards(smsList.value)
            }
        }

    }

    private fun initializeBankCards(smsList: List<SMS?>?) {
        //пройти во всем смс поискать название банка в адрессе и номер карты в теле
    }
}