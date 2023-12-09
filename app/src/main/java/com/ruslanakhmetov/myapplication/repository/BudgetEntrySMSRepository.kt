package com.ruslanakhmetov.myapplication.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ruslanakhmetov.myapplication.database.domain.SMS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BudgetEntrySMSRepository(appContext: Context)  {
    val smsData = SMSData(appContext)
    var smsList:MutableLiveData<List<SMS?>?> = MutableLiveData()

    fun readAllSMS(){
        GlobalScope.launch {
            smsList.value = smsData.readAllSMS()
        }
    }

    fun getSMSList() :MutableLiveData<List<SMS?>?>{
        readAllSMS()
        return smsList
    }

}
