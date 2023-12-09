package com.ruslanakhmetov.myapplication.di.modules

import com.ruslanakhmetov.myapplication.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class AppModule (val  app: App){
    @Singleton
    @Provides
    fun app(): App {
        return app
    }

    @Singleton
    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

}