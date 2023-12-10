package com.ruslanakhmetov.myapplication.repository

import com.ruslanakhmetov.myapplication.domain.BudgetEntry

sealed class AppState(){
    data class Success (val budgetEntries :  List<BudgetEntry>): AppState()
    data class Error   (val error: Throwable) : AppState()
    object Loading: AppState()
}
