package com.example.pizzadiromaapp.presentation.ui

import android.app.Application
import com.example.pizzadiromaapp.di.AppComponent
import com.example.pizzadiromaapp.di.DaggerAppComponent

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

}