package com.ruslanakhmetov.myapplication.database

import androidx.room.TypeConverter
import com.ruslanakhmetov.myapplication.database.domain.OperationType



class OperationTypeConverter {
    @TypeConverter
    fun fromOperationType(value: OperationType): Int {
        return value.id
    }

    @TypeConverter
    fun toOperationType(intValue: Int): OperationType =

        when(intValue){
            1    -> OperationType.EXPENSE
            2    ->OperationType.INCOME
            else -> OperationType.EXPENSE
        }

        //return OperationType.entries.find { it.id == intValue } ?: OperationType.EXPENSE
    }
