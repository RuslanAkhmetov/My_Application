package com.ruslanakhmetov.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslanakhmetov.myapplication.database.AppDatabase
import com.ruslanakhmetov.myapplication.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.domain.SMS
import com.ruslanakhmetov.myapplication.repository.AppState
import com.ruslanakhmetov.myapplication.repository.BudgetEntryRepository
import com.ruslanakhmetov.myapplication.repository.BudgetEntryRepositoryImpl
import com.ruslanakhmetov.myapplication.repository.BudgetEntrySMSRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel() : ViewModel() {
    @Inject
    lateinit var db: AppDatabase
    private val TAG = "MainActivityViewModel"
    private var liveDataToObserve: MutableLiveData<AppState>? = MutableLiveData()
    private val repositoryDB = BudgetEntryRepositoryImpl(db)
    private val repositorySMS = BudgetEntrySMSRepository(App.app)

    private var dbEmpty = MutableLiveData(true)

    suspend fun checkDb() = repositoryDB.getDBCount() == 0


    fun getBudgetEntries() {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataToObserve =
                repositoryDB.getBudgetEntries()?.let { MutableLiveData(it) }
        }
    }


    private fun initializeDataBase() {
        CoroutineScope(Dispatchers.Main).launch {
            dbEmpty = MutableLiveData(checkDb())
            if (dbEmpty.value == true) {
                val smsList = repositorySMS.readAllSMS()

                if (smsList != null && !smsList.isEmpty()) {
                    if (db.bankCardEntityDao.getCount() == 0) {
                        initializeBankCards(smsList)
                    }
                }
            }
        }

    }

    private fun initializeBankCards(smsList: List<SMS?>?) {
        //пройти во всем смс поискать название банка в адрессе и номер карты в теле
    }
}