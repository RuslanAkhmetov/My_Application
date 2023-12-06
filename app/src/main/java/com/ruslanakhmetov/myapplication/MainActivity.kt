package com.ruslanakhmetov.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ruslanakhmetov.myapplication.databinding.ActivityMainBinding
import com.ruslanakhmetov.myapplication.repository.exceloperation.ConvertToExcel
import com.ruslanakhmetov.myapplication.repository.exceloperation.shareFile

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var smsData: SMSData
    lateinit var smsDataMapper: SMSDataMapper
    lateinit var convertToExcel: ConvertToExcel

    companion object {
        const val TAG = "MainActivity"
        val sellers = arrayListOf(
            Seller("METRO.SPB", TransactionSource.ТРАНСПОРТ),
            Seller("APTECHNOE", TransactionSource.ПРОДУКТЫ),
            Seller("DIXI-78788D", TransactionSource.ПРОДУКТЫ),
            Seller("BUSHE", TransactionSource.РАЗВЛЕЧЕНИЯ),
            Seller("bilet.nspk", TransactionSource.ТРАНСПОРТ)
        )

        val bankCards = arrayListOf(
            BankCard("Tinkoff", "*0345", 0.0)
        )

        const val FILE_NAME = "report.xls"
        const val REPORT_DIR = "reports"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        smsData = SMSData(applicationContext)
        smsDataMapper = SMSDataMapper(sellers, bankCards)
        convertToExcel = ConvertToExcel(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.share.setOnClickListener {
                shareFile(this, REPORT_DIR, FILE_NAME)

        }
    }

    override fun onStart() {
        super.onStart()
        if (checkSelfPermission("android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf("android.permission.READ_SMS"), 2)
        } else {

            val smsList = smsData.readSMSAfterDate(1700636623066L)
                ?.let { smslist ->    //readSMSFromSender("HoroshSvyaz")  readSMSAfterDate(1700234258465)
                    var budgetEntries = mutableListOf<BudgetEntry>()
                    for (sms in smslist) {
                        sms?.let {
                            if (sms.sender.equals(bankCards[0].bankName)) {
                                smsDataMapper.convertSMSToBudgetEntry(it)?.let { entry ->
                                    budgetEntries.add(entry)
                                    Log.i(TAG, "onStart ......`")
                                    Log.i(TAG, "smsId: ${entry?.smsId}  ")
                                    Log.i(TAG, "cardPan: ${entry?.cardPan}  ")
                                    Log.i(TAG, "trSource: ${entry?.transactionSource}  ")
                                    Log.i(TAG, "date: ${entry?.date.toString()}  ")
                                    Log.i(TAG, "amount: ${entry?.operationAmount.toString()}  ")
                                    Log.i(TAG, "balance: ${bankCards[0].balance}")
                                }


                            }
                        }
                    }
                    budgetEntries?.let {
                        convertToExcel.convertBudgetEntryToExcel(
                            dirName = REPORT_DIR,
                            fileName = FILE_NAME,
                            dataList = budgetEntries
                        )

                    }
                }
        }

    }
}