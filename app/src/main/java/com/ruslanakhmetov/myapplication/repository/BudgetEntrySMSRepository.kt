package com.ruslanakhmetov.myapplication.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ruslanakhmetov.myapplication.domain.SMS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BudgetEntrySMSRepository(appContext: Context)  {
    val smsData = SMSData(appContext)

    suspend fun readAllSMS():List<SMS?>? =
        smsData.readAllSMS()



}
