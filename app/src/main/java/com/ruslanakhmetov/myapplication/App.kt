package com.ruslanakhmetov.myapplication

import android.app.Application
import android.content.Context

class App: Application() {

}

val Context.app : App
    get() {
        return applicationContext as App
    }