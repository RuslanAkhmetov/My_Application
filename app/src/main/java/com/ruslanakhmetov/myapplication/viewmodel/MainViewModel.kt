package com.ruslanakhmetov.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.ruslanakhmetov.myapplication.database.AppDatabase
import com.ruslanakhmetov.myapplication.database.domain.BudgetEntry
import com.ruslanakhmetov.myapplication.repository.BudgetEntryRepositoryImpl
import javax.inject.Inject

class MainViewModel(): ViewModel() {
    @Inject
    lateinit var  db : AppDatabase
    private val dbRepository = BudgetEntryRepositoryImpl(db)

    private fun getBudgetEntitriesFromDB(): MutableLiveData<List<BudgetEntry>>{
        return dbRepository.getBudgetEntries()
    }
}