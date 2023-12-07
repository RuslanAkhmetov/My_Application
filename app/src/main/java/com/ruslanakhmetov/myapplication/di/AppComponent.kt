package com.ruslanakhmetov.myapplication.di

import com.ruslanakhmetov.myapplication.di.modules.DataBaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataBaseModule::class]
)
interface AppComponent {

}