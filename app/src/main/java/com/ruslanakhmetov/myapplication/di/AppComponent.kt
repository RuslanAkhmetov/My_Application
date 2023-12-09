package com.ruslanakhmetov.myapplication.di

import com.ruslanakhmetov.myapplication.di.modules.AppModule
import com.ruslanakhmetov.myapplication.di.modules.DataBaseModule
import com.ruslanakhmetov.myapplication.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataBaseModule::class, AppModule::class]
)
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)


}