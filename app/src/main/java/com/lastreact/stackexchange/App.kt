package com.lastreact.stackexchange

import android.app.Application
import com.lastreact.stackexchange.di.ApplicationComponent
import com.lastreact.stackexchange.di.DaggerApplicationComponent

class App: Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}