package com.ruslanakhmetov.myapplication.di.modules

import androidx.room.Room
import com.ruslanakhmetov.myapplication.App
import com.ruslanakhmetov.myapplication.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

}