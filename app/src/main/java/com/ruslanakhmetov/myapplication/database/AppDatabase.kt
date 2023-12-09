package com.ruslanakhmetov.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ruslanakhmetov.myapplication.database.dao.BankCardEntityDao
import com.ruslanakhmetov.myapplication.database.dao.BudgetEntryEntityDao
import com.ruslanakhmetov.myapplication.database.dao.BudgetGroupEntityDao
import com.ruslanakhmetov.myapplication.database.dao.SmsDataDao
import com.ruslanakhmetov.myapplication.database.domain.BankCard


@Database(
    entities = [SmsDataEntity::class, BudgetGroupEntity::class, BudgetEntryEntity::class, BankCardEntity::class],
    version = 1
)
@TypeConverters(OperationTypeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "AppDatabase.db"
    }
    abstract val smsDataDao: SmsDataDao
    abstract val budgetGroupEntityDao: BudgetGroupEntityDao
    abstract val budgetEntryEntityDao: BudgetEntryEntityDao
    abstract val bankCardEntityDao: BankCardEntityDao
}




