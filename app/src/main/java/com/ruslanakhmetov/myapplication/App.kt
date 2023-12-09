package com.ruslanakhmetov.myapplication

import android.app.Application
import android.content.Context
import com.ruslanakhmetov.myapplication.di.AppComponent
import com.ruslanakhmetov.myapplication.di.DaggerAppComponent
import com.ruslanakhmetov.myapplication.di.modules.AppModule

class App: Application() {
        lateinit var appComponent: AppComponent
            private set

        companion object{
            lateinit var app: App
        }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}

val Context.app : App
    get() {
        return applicationContext as App
    }