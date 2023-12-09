package com.ruslanakhmetov.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ruslanakhmetov.myapplication.database.domain.BudgetEntry
import io.reactivex.rxjava3.core.Single

interface BudgetEntryRepository {

    fun getBudgetEntries() : MutableLiveData<AppState>

}